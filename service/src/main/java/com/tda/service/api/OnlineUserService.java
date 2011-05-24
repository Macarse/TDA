package com.tda.service.api;

import java.util.List;

import com.tda.model.applicationuser.OnlineUser;


public interface OnlineUserService {
	void setOnline(String username);
	void setOffline(String username);
	List<OnlineUser> getOnlineUsers();
	void clearOffline();
}
