package com.tda.presentation.client.datasource;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.patient.Patient;
import com.tda.model.patient.PatientBuilder;
import com.tda.model.patient.Sex;
import com.tda.presentation.client.exception.NotInitializedException;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

public class PatientGwtRPCDS extends CrudGwtRPCDS<Patient> {

	private static CrudServiceGWTWrapperAsync<Patient> rpc;
	private static PatientGwtRPCDS _instance;

	private static final String ID = "id";
	private static final String DNI = "dni";
	private static final String NAME = "name";
	private static final String SEX = "sex";
	private static final String BIRTHDATE = "birthdate";
	private static final String FATHERNAME = "fathername";
	private static final String MOTHERNAME = "mothername";

	public static PatientGwtRPCDS getInstance() throws NotInitializedException {
		if (_instance == null)
			throw new NotInitializedException("PatientGwtRPCDS");

		return _instance;
	}

	public static void setRpc(CrudServiceGWTWrapperAsync<Patient> service) {
		if (_instance == null) {
			rpc = service;
			_instance = new PatientGwtRPCDS(rpc);
		}
	}

	private PatientGwtRPCDS(CrudServiceGWTWrapperAsync<Patient> rpc) {
		setID("PatientsDataSource");

		DataSourceIntegerField idField = new DataSourceIntegerField(ID, "Id");
		idField.setType(FieldType.SEQUENCE);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		addField(idField);

		DataSourceTextField nameField = new DataSourceTextField(NAME, "Nombre");
		nameField.setRequired(true);
		nameField.setType(FieldType.TEXT);
		addField(nameField);

		DataSourceIntegerField dniField = new DataSourceIntegerField(DNI, "DNI");
		dniField.setRequired(true);
		dniField.setType(FieldType.INTEGER);
		addField(dniField);

		DataSourceEnumField sexField = new DataSourceEnumField(SEX, "Sexo");
		sexField.setRequired(true);
		sexField.setType(FieldType.ENUM);
		SelectItem sexSelect = new SelectItem();
		sexSelect.setDefaultValue(Sex.male.toString());
		sexSelect.setValueMap(Sex.getMap());
		sexField.setEditorType(sexSelect);
		addField(sexField);

		DataSourceTextField birthdateField = new DataSourceTextField(BIRTHDATE,
				"Nacimiento");
		birthdateField.setRequired(true);
		birthdateField.setType(FieldType.TEXT);
		addField(birthdateField);

		DataSourceTextField fatherNameField = new DataSourceTextField(
				FATHERNAME, "Padre");
		fatherNameField.setRequired(true);
		fatherNameField.setType(FieldType.TEXT);
		addField(fatherNameField);

		DataSourceTextField motherNameField = new DataSourceTextField(
				MOTHERNAME, "Madre");
		motherNameField.setRequired(true);
		motherNameField.setType(FieldType.TEXT);
		addField(motherNameField);

	}

	@Override
	public void copyValues(Record record, DynamicForm form) {
		form.setValue(ID, record.getAttribute(ID));
		form.setValue(NAME, record.getAttribute(NAME));
		form.setValue(DNI, record.getAttribute(DNI));
		form.setValue(SEX, record.getAttribute(SEX));
		form.setValue(BIRTHDATE, record.getAttribute(BIRTHDATE));
		form.setValue(FATHERNAME, record.getAttribute(FATHERNAME));
		form.setValue(MOTHERNAME, record.getAttribute(MOTHERNAME));
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

		rpc.findAll(new AsyncCallback<List<Patient>>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(List<Patient> result) {
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
	protected void copyValues(Patient from, ListGridRecord to) {

		to.setAttribute(ID, from.getId().toString());
		to.setAttribute(NAME, from.getName());

		if (from.getDni() != null) {
			to.setAttribute(DNI, from.getDni());
		}

		if (from.getSex() != null) {
			to.setAttribute(SEX, from.getSex());
		}

		if (from.getBirthdate() != null) {
			to.setAttribute(BIRTHDATE, from.getBirthdate());
		}

		if (from.getFatherName() != null) {
			to.setAttribute(FATHERNAME, from.getFatherName());
		}

		if (from.getMotherName() != null) {
			to.setAttribute(MOTHERNAME, from.getMotherName());
		}
	}

	@Override
	protected CrudServiceGWTWrapperAsync<Patient> getRpc() {
		return rpc;
	}

	@Override
	public Patient get(DynamicForm form) {

		Patient patient = PatientBuilder
				.createPatient()
				.withName(form.getValueAsString(NAME))
				.withDni(form.getValueAsString(DNI))
				// .withBirthdate(new Date(form.getValueAsString(BIRTHDATE)))
				.withBirthdate(new Date())
				.withFatherName(form.getValueAsString(FATHERNAME))
				.withMohterName(form.getValueAsString(MOTHERNAME)).build();

		String idString = form.getValueAsString(ID);
		if (idString != null) {
			patient.setId(Long.valueOf(idString));
		}

		return patient;
	}
}
