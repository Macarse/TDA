package com.tda.presentation.client.view;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class ItemsDataSource extends DataSource {

	public ItemsDataSource() {
		setID("ItemsDataSource");

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
