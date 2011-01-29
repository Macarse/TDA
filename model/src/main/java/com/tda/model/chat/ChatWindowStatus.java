package com.tda.model.chat;

import java.util.HashMap;

public class ChatWindowStatus {
	private HashMap<String, Integer> chatListS = new HashMap<String, Integer>();  
	
	public void setStatus(String name, int status){
		chatListS.put(name, status);
	}
	
	public HashMap<String, Integer> getList(){
		return chatListS;
	}
	
}
