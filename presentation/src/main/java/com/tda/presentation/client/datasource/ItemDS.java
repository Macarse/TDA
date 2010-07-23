package com.tda.presentation.client.datasource;

import java.util.List;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class ItemDS extends DataSource {
	
	public ItemDS() {
		setID("ItemsDataSource");

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
	
	public ItemDS(Record record){
		this();
		
		this.addData(record);
	}
	
	public ItemDS(List<Record> records){
		this();
		
		for (Record record : records) {
			this.addData(record);			
		}
	}
}
