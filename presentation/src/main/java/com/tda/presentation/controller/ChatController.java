package com.tda.presentation.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.tda.model.chat.ChatMessage;
import com.tda.model.chat.ChatWindowStatus;
import com.tda.model.chat.HeartbeatResponse;
import com.tda.model.chat.Item;
import com.tda.model.chat.StartSessionResponse2;
import com.tda.presentation.session.ChatSession;
import com.tda.service.api.ChatService;

@Controller
@RequestMapping(value = "/chat")
@SessionAttributes({"user"})
public class ChatController {

	private ChatService chatService;
	private ChatSession chatSession;
	private ChatWindowStatus chatWStatus = new ChatWindowStatus();
	
	@ModelAttribute("user")
	public UserDetails getUser() {
		Object aux = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return ((UserDetails) aux);
	}

	@Autowired
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	@Autowired
	public void setChatSession(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public @ResponseBody
	void send(@RequestParam String to, @RequestParam String message) {
		String nMessage = ChatController.escapeHTML(message); 
		chatService.send(chatSession.getUsername(), to, nMessage);
		chatSession.addMessage(chatSession.getUsername(), to, nMessage);
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public @ResponseBody
	void send(@RequestParam String chatbox) {
		chatWStatus.setStatus(chatbox, 0);
	}
	
	@RequestMapping(value = "/minimize", method = RequestMethod.POST)
	public @ResponseBody
	void minimize(@RequestParam String to) {
		chatWStatus.setStatus(to, 1);
	}
	
	@RequestMapping(value = "/open", method = RequestMethod.POST)
	public @ResponseBody
	void open(@RequestParam String to) {
		chatWStatus.setStatus(to, 2);
	}

	@RequestMapping(value = "/startchatsession", method = RequestMethod.GET)
	public @ResponseBody
	String startchatsession() {

		chatSession.setUsername(getUser().getUsername());
		
		Gson gson = new Gson();
		List<ChatMessage> msgs = chatSession.getMessages();
		LinkedList<Item> items = new LinkedList<Item>();

		for (ChatMessage chatMessage : msgs) {
			Item i = new Item();
			if (chatMessage.getFromWhom().equals(chatSession.getUsername())){
				i.setF(chatMessage.getToWhom());
				i.setS("1");
			}else{
				i.setF(chatMessage.getFromWhom());
				i.setS("0");
			}
			i.setM(chatMessage.getMessage());
			items.add(i);
		}

		StartSessionResponse2 ret = new StartSessionResponse2();
		ret.setUsername(chatSession.getUsername());
		ret.setItems(items);
		ret.setWindowStatus(chatWStatus.getList());

		return gson.toJson(ret);
	}

	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public @ResponseBody
	String heartbeat(@RequestParam(required = false) String action) {
		Gson gson = new Gson();
		List<ChatMessage> msgs = chatService.chatHeartbeat(chatSession.getUsername());
		LinkedList<Item> items = new LinkedList<Item>();

		for (ChatMessage chatMessage : msgs) {
			//add msg to session
			chatSession.addMessage(chatMessage.getFromWhom(),chatMessage.getToWhom(), chatMessage.getMessage());
			
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

	private static final String escapeHTML(String s){
		   StringBuffer sb = new StringBuffer();
		   int n = s.length();
		   for (int i = 0; i < n; i++) {
		      char c = s.charAt(i);
		      switch (c) {
		         case '<': sb.append("&lt;"); break;
		         case '>': sb.append("&gt;"); break;
		         case '&': sb.append("&amp;"); break;
		         case '"': sb.append("&quot;"); break;
		         case 'á': sb.append("&aacute;");break;
		         case 'à': sb.append("&agrave;");break;
		         case 'À': sb.append("&Agrave;");break;
		         case 'â': sb.append("&acirc;");break;
		         case 'Â': sb.append("&Acirc;");break;
		         case 'ä': sb.append("&auml;");break;
		         case 'Ä': sb.append("&Auml;");break;
		         case 'å': sb.append("&aring;");break;
		         case 'Å': sb.append("&Aring;");break;
		         case 'æ': sb.append("&aelig;");break;
		         case 'Æ': sb.append("&AElig;");break;
		         case 'ç': sb.append("&ccedil;");break;
		         case 'Ç': sb.append("&Ccedil;");break;
		         case 'é': sb.append("&eacute;");break;
		         case 'É': sb.append("&Eacute;");break;
		         case 'è': sb.append("&egrave;");break;
		         case 'È': sb.append("&Egrave;");break;
		         case 'ê': sb.append("&ecirc;");break;
		         case 'Ê': sb.append("&Ecirc;");break;
		         case 'ë': sb.append("&euml;");break;
		         case 'Ë': sb.append("&Euml;");break;
		         case 'ó': sb.append("&iuml;");break;
		         case 'í': sb.append("&iacute;");break;
		         case 'Í': sb.append("&Iacute;");break;
		         case 'ï': sb.append("&iuml;");break;
		         case 'Ï': sb.append("&Iuml;");break;
		         case 'ô': sb.append("&ocirc;");break;
		         case 'Ô': sb.append("&Ocirc;");break;
		         case 'ö': sb.append("&ouml;");break;
		         case 'Ö': sb.append("&Ouml;");break;
		         case 'ø': sb.append("&oslash;");break;
		         case 'Ø': sb.append("&Oslash;");break;
		         case 'ß': sb.append("&szlig;");break;
		         case 'ù': sb.append("&ugrave;");break;
		         case 'Ù': sb.append("&Ugrave;");break;         
		         case 'û': sb.append("&ucirc;");break;         
		         case 'Û': sb.append("&Ucirc;");break;
		         case 'ü': sb.append("&uuml;");break;
		         case 'Ü': sb.append("&Uuml;");break;
		         case '®': sb.append("&reg;");break;         
		         case '©': sb.append("&copy;");break;   
		         case '€': sb.append("&euro;"); break;
		         case 'Ó': sb.append("&Oacute;");break;
		         case 'u': sb.append("&uacute;");break;
		         case 'Ú': sb.append("&uacute;");break;
		         
		         // be carefull with this one (non-breaking whitee space)
		         case ' ': sb.append("&nbsp;");break;         
		         
		         default:  sb.append(c); break;
		      }
		   }
		   return sb.toString();
		}
}
