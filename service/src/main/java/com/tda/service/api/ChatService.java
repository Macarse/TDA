package com.tda.service.api;

import java.util.List;

import com.tda.model.chat.ChatMessage;


public interface ChatService {
	List<ChatMessage> chatHeartbeat(String username);
	void send(String from, String to, String message);
	List<ChatMessage> startSession(String username);
}
