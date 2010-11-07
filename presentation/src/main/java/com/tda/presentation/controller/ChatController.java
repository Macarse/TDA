package com.tda.presentation.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tda.model.chat.ChatMessage;
import com.tda.model.chat.HeartbeatResponse;
import com.tda.model.chat.Item;
import com.tda.model.chat.StartSessionResponse2;
import com.tda.presentation.session.ChatSession;
import com.tda.service.api.ChatService;

@Controller
@RequestMapping(value = "/chat")
public class ChatController {

	private ChatService chatService;
	private ChatSession chatSession;

	@Autowired
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	@Autowired
	public void setChatSession(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public @ResponseBody
	void send(@RequestParam String to, @RequestParam String message) {
		chatService.send("Me", to, message);
	}

	@RequestMapping(value = "/startchatsession", method = RequestMethod.GET)
	public @ResponseBody
	String startchatsession() {

		System.out.println("In startchatsession");
		Gson gson = new Gson();
		StartSessionResponse2 ret = new StartSessionResponse2();
		ret.setUsername("nickname");
		ret.setItems(new LinkedList<Item>());

		chatSession.setUsername("nickname");
		
		return gson.toJson(ret);
	}

	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public @ResponseBody
	String heartbeat(@RequestParam(required = false) String action) {

		System.out.println("In heartbeat");
		Gson gson = new Gson();
		List<ChatMessage> msgs = chatService.chatHeartbeat(chatSession.getUsername());
		LinkedList<Item> items = new LinkedList<Item>();

		for (ChatMessage chatMessage : msgs) {
			Item i = new Item();
			i.setF(chatMessage.getFromWhom());
			i.setM(chatMessage.getMessage());
			i.setS("0");
			items.add(i);
		}

		HeartbeatResponse ret = new HeartbeatResponse();
		ret.setItems(items);

		return gson.toJson(ret);
	}

}
