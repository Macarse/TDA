package com.tda.presentation.client.view;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class ItemsDataSource extends DataSource {

	private static ItemsDataSource _instance;

	public static ItemsDataSource getInstance() {
		if ( _instance == null ) {
			_instance = new ItemsDataSource();
		}

		return _instance;
	}

	private ItemsDataSource() {
		setID("ItemsDataSource");

		/* Just use the data from the client. It will not fetch data with
		 * the rpc call */
		setClientOnly(true);

		DataSourceIntegerField idField = new DataSourceIntegerField("id", "Id");
		idField.setType(FieldType.SEQUENCE);
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		addField(idField);

		DataSourceTextField nameField = new DataSourceTextField("name", "Nombre");
		nameField.setRequired(true);
		nameField.setType(FieldType.TEXT);
		setFields(nameField);		
	}
}
