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
		return chatDAO.findByExample(exampleObject);
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

}
