package com.tda.persistence.dao;

import com.tda.model.chat.ChatMessage;

public class ChatDAO extends GenericDAOImpl<ChatMessage> {

	@Override
	protected Class<ChatMessage> getDomainClass() {
		return ChatMessage.class;
	}
}
