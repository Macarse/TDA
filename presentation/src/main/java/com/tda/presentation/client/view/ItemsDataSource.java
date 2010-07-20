package com.tda.presentation.client.view;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class ItemsDataSource extends DataSource {

	public ItemsDataSource() {
		setID("ItemsDataSource");

		setClientOnly(true);
		DataSourceTextField idField = new DataSourceTextField("id", "id");
		idField.setRequired(true);
		idField.setPrimaryKey(true);
		addField(idField);

		DataSourceTextField nameField = new DataSourceTextField("name", "Nombre");
		nameField.setRequired(true);
		addField(nameField);		
	}
}
