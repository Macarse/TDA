package com.tda.presentation.client.datasource;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.applicationuser.ApplicationUserGWT;
import com.tda.model.applicationuser.Authorities;
import com.tda.model.applicationuser.Authority;
import com.tda.presentation.client.exception.NotInitializedException;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

public class ApplicationUserGwtRPCDS extends CrudGwtRPCDS<ApplicationUserGWT> {

	private static CrudServiceGWTWrapperAsync<ApplicationUserGWT> rpc;
	private static ApplicationUserGwtRPCDS _instance;

	private static final String AUTHORITIES = "authorities";
	private static final String IS_ENABLED = "isenabled";
	private static final String IS_CREDENTIAL_NON_EXPIRED = "iscredentialnonexpired";
	private static final String IS_ACCOUNT_NON_LOCKED = "isaccountnonlocked";
	private static final String IS_ACCOUNT_NON_EXPIRED = "isaccountnonexpired";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String ID = "id";

	public static ApplicationUserGwtRPCDS getInstance()
			throws NotInitializedException {
		if (_instance == null)
			throw new NotInitializedException("ApplicationUserGWTGwtRPCDS");

		return _instance;
	}

	public static void setRpc(
			CrudServiceGWTWrapperAsync<ApplicationUserGWT> service) {
		if (_instance == null) {
			rpc = service;
			_instance = new ApplicationUserGwtRPCDS(rpc);
		}
	}

	private ApplicationUserGwtRPCDS(
			CrudServiceGWTWrapperAsync<ApplicationUserGWT> rpc) {
		setID("ApplicationUserGWTsDataSource");

		DataSourceIntegerField idField = new DataSourceIntegerField(ID, "Id");
		idField.setType(FieldType.SEQUENCE);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		addField(idField);

		DataSourceTextField userNameField = new DataSourceTextField(USERNAME,
				"Usuario");
		userNameField.setRequired(true);
		userNameField.setType(FieldType.TEXT);
		addField(userNameField);

		DataSourcePasswordField passwordField = new DataSourcePasswordField(
				PASSWORD, "Contraseña");
		passwordField.setRequired(true);
		passwordField.setType(FieldType.PASSWORD);
		addField(passwordField);

		final DataSourceEnumField authoritiesField = new DataSourceEnumField(
				AUTHORITIES, "Rol");
		authoritiesField.setRequired(true);
		authoritiesField.setType(FieldType.ENUM);
		authoritiesField.setValueMap(Authorities.getMap());
		addField(authoritiesField);

		DataSourceBooleanField nonExpiredField = new DataSourceBooleanField(
				IS_ACCOUNT_NON_EXPIRED, "Expirado");
		nonExpiredField.setRequired(false);
		nonExpiredField.setType(FieldType.BOOLEAN);
		addField(nonExpiredField);

		DataSourceBooleanField nonLockedField = new DataSourceBooleanField(
				IS_ACCOUNT_NON_LOCKED, "Bloqueado");
		nonLockedField.setRequired(false);
		nonLockedField.setType(FieldType.BOOLEAN);
		addField(nonLockedField);

		DataSourceBooleanField credentialNonExpiredField = new DataSourceBooleanField(
				IS_CREDENTIAL_NON_EXPIRED, "Credenciales Expiradas");
		credentialNonExpiredField.setRequired(false);
		credentialNonExpiredField.setType(FieldType.BOOLEAN);
		addField(credentialNonExpiredField);

		DataSourceBooleanField enabledField = new DataSourceBooleanField(
				IS_ENABLED, "Habilitado");
		enabledField.setRequired(false);
		enabledField.setType(FieldType.BOOLEAN);
		addField(enabledField);
	}

	@Override
	public void copyValues(Record record, DynamicForm form) {
		form.setValue(ID, record.getAttribute(ID));
		form.setValue(USERNAME, record.getAttribute(USERNAME));
		form.setValue(PASSWORD, record.getAttribute(PASSWORD));
		form.setValue(IS_ACCOUNT_NON_EXPIRED,
				record.getAttribute(IS_ACCOUNT_NON_EXPIRED));
		form.setValue(IS_ACCOUNT_NON_LOCKED,
				record.getAttribute(IS_ACCOUNT_NON_LOCKED));
		form.setValue(IS_CREDENTIAL_NON_EXPIRED,
				record.getAttribute(IS_CREDENTIAL_NON_EXPIRED));
		form.setValue(IS_ENABLED, record.getAttribute(IS_ENABLED));
		form.setValue(AUTHORITIES,
				Authorities.getKey(record.getAttribute(AUTHORITIES)));
	}

	@Override
	protected void executeFetch(final String requestId,
			final DSRequest request, final DSResponse response) {
		// This can be used as parameter for server side sorting.
		// request.getSortBy ();

		// Finding which rows were requested
		// Normally these two indexes should be passed to server
		// but for this example I will do "paging" on client side
		final int startIndex = (request.getStartRow() < 0) ? 0 : request
				.getStartRow();
		final int endIndex = (request.getEndRow() == null) ? -1 : request
				.getEndRow();

		rpc.findAll(new AsyncCallback<List<ApplicationUserGWT>>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(List<ApplicationUserGWT> result) {
				// Calculating size of return list
				int size = result.size();
				if (endIndex >= 0) {
					if (endIndex < startIndex) {
						size = 0;
					} else {
						size = endIndex - startIndex + 1;
					}
				}
				// Create list for return - it is just requested records
				ListGridRecord[] list = new ListGridRecord[size];
				if (size > 0) {
					for (int i = 0; i < result.size(); i++) {
						if (i >= startIndex && i <= endIndex) {
							ListGridRecord record = new ListGridRecord();
							copyValues(result.get(i), record);
							list[i - startIndex] = record;
						}
					}
				}
				response.setData(list);
				// IMPORTANT: for paging to work we have to specify size of full
				// result set
				response.setTotalRows(result.size());
				processResponse(requestId, response);
			}
		});
	}

	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
	}

	@Override
	protected void executeUpdate(final String requestId,
			final DSRequest request, final DSResponse response) {
	}

	@Override
	protected void executeRemove(final String requestId,
			final DSRequest request, final DSResponse response) {
	}

	@Override
	protected void copyValues(ApplicationUserGWT from, ListGridRecord to) {

		to.setAttribute(ID, from.getId().toString());
		to.setAttribute(USERNAME, from.getUsername());
		to.setAttribute(PASSWORD, from.getPassword());

		if (from.getMyAuthorities() != null) {
			Authority auth = (Authority) from.getMyAuthorities().toArray()[0];
			to.setAttribute(AUTHORITIES,
					Authorities.getName(auth.getAuthority()));
		}

		to.setAttribute(IS_ACCOUNT_NON_EXPIRED, from.isAccountNonExpired());
		to.setAttribute(IS_ACCOUNT_NON_LOCKED, from.isAccountNonLocked());
		to.setAttribute(IS_CREDENTIAL_NON_EXPIRED, from.isAccountNonExpired());
		to.setAttribute(IS_ENABLED, from.isEnabled());
	}

	@Override
	protected CrudServiceGWTWrapperAsync<ApplicationUserGWT> getRpc() {
		return rpc;
	}

	@Override
	public ApplicationUserGWT get(DynamicForm form) {

		ApplicationUserGWT applicationUserGWT = new ApplicationUserGWT();
		applicationUserGWT.setUsername(form.getValueAsString(USERNAME));
		applicationUserGWT.setPassword(form.getValueAsString(PASSWORD));
		applicationUserGWT.setEnabled(Boolean.parseBoolean(form
				.getValueAsString(IS_ENABLED)));
		applicationUserGWT.setAccountNonExpired(Boolean.parseBoolean(form
				.getValueAsString(IS_ACCOUNT_NON_EXPIRED)));
		applicationUserGWT.setAccountNonLocked(Boolean.parseBoolean(form
				.getValueAsString(IS_ACCOUNT_NON_LOCKED)));
		applicationUserGWT.setCredentialsNonExpired(Boolean.parseBoolean(form
				.getValueAsString(IS_CREDENTIAL_NON_EXPIRED)));

		List<Authority> authList = new ArrayList<Authority>();
		Authority auth = new Authority();
		auth.setId(-1L);
		auth.setAuthority(Authorities.valueOf(
				form.getValueAsString(AUTHORITIES)).toString());
		authList.add(auth);

		applicationUserGWT.setMyAuthorities(authList);

		String idString = form.getValueAsString(ID);
		if (idString != null) {
			applicationUserGWT.setId(Long.valueOf(idString));
		}

		return applicationUserGWT;
	}
}
