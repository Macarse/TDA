package com.tda.model.chat;

import java.util.HashMap;
import java.util.LinkedList;

public class StartSessionResponse2 {
	private String username;
	private LinkedList<Item> items;
	private HashMap<String, Integer> windowStatus;

	public HashMap<String, Integer> getWindowStatus() {
		return this.windowStatus;
	}
	

	public void setWindowStatus(HashMap<String, Integer> ws){
		this.windowStatus = ws;
	}


	public StartSessionResponse2() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LinkedList<Item> getItems() {
		return items;
	}

	public void setItems(LinkedList<Item> items) {
		this.items = items;
	}

}
