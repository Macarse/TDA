package com.tda.presentation.session;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.tda.model.chat.ChatMessage;

public class ChatSession implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private LinkedList<String> openChatBoxes;
	private LinkedHashMap<String, LinkedList<ChatMessage>> msgs = new LinkedHashMap<String, LinkedList<ChatMessage>>();
	
	public ChatSession() {
		openChatBoxes = new LinkedList<String>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void addMessage(String from, String toWhom, String message){
		LinkedList<ChatMessage> listMsgs;
		
		if (msgs.containsKey(toWhom) == false){
			msgs.put(toWhom, new LinkedList<ChatMessage>());
		}
		
		listMsgs = msgs.get(toWhom);
		listMsgs.add(new ChatMessage(from,toWhom,message));
	}
	
	public void addMessage(String toWhom, String message){
		LinkedList<ChatMessage> listMsgs;
		
		if (msgs.containsKey(toWhom) == false){
			msgs.put(toWhom, new LinkedList<ChatMessage>());
		}
		
		listMsgs = msgs.get(toWhom);
		listMsgs.add(new ChatMessage(username,toWhom,message));
	}
	
	public List<ChatMessage> getMessages(){
		LinkedList<ChatMessage> list = new LinkedList<ChatMessage>();
		
		for (LinkedList<ChatMessage> msgsList : msgs.values()) {
			list.addAll(msgsList);
		}
		
		return list;
	}
	
}
 