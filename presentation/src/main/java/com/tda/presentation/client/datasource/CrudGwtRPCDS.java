package com.tda.presentation.client.datasource;

import java.util.List;

import org.hibernate.hql.ast.tree.DotNode.IllegalCollectionDereferenceExceptionBuilder;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

/**
 * Example <code>GwtRpcDataSource</code> implementation.
 * 
 * @author Aleksandras Novikovas
 * @author System Tier
 * @version 1.0
 */
public abstract class CrudGwtRPCDS<T> extends GwtRpcDataSource {

	protected abstract CrudServiceGWTWrapperAsync<T> getRpc();

	/*
	 * Copy values from record to form
	 */
	public abstract void copyValues(Record record, DynamicForm form);

	/*
	 * Given a form returns an record
	 */
	public abstract T get(DynamicForm form);

	/*
	 * Given a item returns a listgridrecord
	 */
	protected abstract void copyValues(T from, ListGridRecord to);

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

		getRpc().findAll(new AsyncCallback<List<T>>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(List<T> result) {
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

}
