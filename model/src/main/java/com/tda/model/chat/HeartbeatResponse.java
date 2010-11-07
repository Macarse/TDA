package com.tda.model.chat;

import java.util.LinkedList;

public class HeartbeatResponse {
	private LinkedList<Item> items;

	public HeartbeatResponse() {
		
	}

	public LinkedList<Item> getItems() {
		return items;
	}

	public void setItems(LinkedList<Item> items) {
		this.items = items;
	}
	
	
}
