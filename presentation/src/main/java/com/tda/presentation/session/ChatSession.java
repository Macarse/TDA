package com.tda.presentation.session;

import java.util.LinkedList;

public class ChatSession {
	private String username;
	private LinkedList<String> openChatBoxes;
	
	public ChatSession() {
		openChatBoxes = new LinkedList<String>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
