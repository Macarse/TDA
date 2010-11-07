package com.tda.service.impl;

import java.util.Date;
import java.util.List;

import com.tda.model.chat.ChatMessage;
import com.tda.persistence.dao.ChatDAO;
import com.tda.service.api.ChatService;

public class ChatServiceImpl implements ChatService {

	private ChatDAO chatDAO;

	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}

	public List<ChatMessage> chatHeartbeat(String username) {
		ChatMessage exampleObject = new ChatMessage();
		exampleObject.setToWhom(username);
		exampleObject.setRecd(0);
		List<ChatMessage> msgs = chatDAO.findByExample(exampleObject);
		
		//update read
		for (ChatMessage chatMessage : msgs) {
			chatMessage.setRecd(1);
			chatDAO.save(chatMessage);
		}
		
		
		return msgs;
	}

	public void send(String from, String to, String message) {
		ChatMessage msg = new ChatMessage();
		msg.setFromWhom(from);
		msg.setToWhom(to);
		msg.setMessage(message);
		msg.setSent(new Date());
		msg.setRecd(0);

		System.out.println(msg);

		chatDAO.save(msg);
	}
	
	public List<ChatMessage> startSession(String username) {
		ChatMessage exampleObject = new ChatMessage();
		exampleObject.setToWhom(username);
		exampleObject.setRecd(1);
		List<ChatMessage> msgs = chatDAO.findByExample(exampleObject);
		
		return msgs;
	}

}
