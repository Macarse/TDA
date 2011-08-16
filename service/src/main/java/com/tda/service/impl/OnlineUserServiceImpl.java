package com.tda.service.impl;

import java.util.Calendar;
import java.util.List;

import com.tda.model.applicationuser.OnlineUser;
import com.tda.persistence.dao.OnlineUserDAO;
import com.tda.service.api.OnlineUserService;

public class OnlineUserServiceImpl implements OnlineUserService {

	private OnlineUserDAO onlineUserDAO;

	public void setOnlineUserDAO(OnlineUserDAO onlineUserDAO) {
		this.onlineUserDAO = onlineUserDAO;
	}

	public void setOnline(String username) {
		OnlineUser user = new OnlineUser();
		user.setUsername(username);

		Calendar calendar = Calendar.getInstance();
		// add timeout
		calendar.add(Calendar.SECOND, 10);

		user.setTimeout(calendar.getTime());

		onlineUserDAO.save(user);
	}

	public void setOffline(String username) {
		onlineUserDAO.deleteById(username);

	}

	public List<OnlineUser> getOnlineUsers() {
		return onlineUserDAO.findAll();
	}

	public void clearOffline() {
		onlineUserDAO.deleteTimeoutUsers();
	}

}
