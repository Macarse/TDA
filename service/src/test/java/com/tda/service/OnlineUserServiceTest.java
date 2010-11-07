package com.tda.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.applicationuser.OnlineUser;
import com.tda.service.api.OnlineUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-persistence.xml",
		"classpath:/test-datasource.xml", "classpath:/spring-service.xml",
		"classpath:/spring-security.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OnlineUserServiceTest {

	@Autowired
	private OnlineUserService onlineUserService;


	@Test
	public void setOnline() {
		onlineUserService.setOnline("user1");
		onlineUserService.setOnline("user2");
		
		List<OnlineUser> onlineUsers = onlineUserService.getOnlineUsers();

		Assert.assertTrue(onlineUsers.size() == 2);
	}

	@Test
	public void setOffline() {
		onlineUserService.setOnline("user1");
		List<OnlineUser> onlineUsers;
		
		onlineUsers = onlineUserService.getOnlineUsers();
		Assert.assertEquals("user1", onlineUsers.get(0).getUsername());
		
		onlineUserService.setOffline("user1");
		onlineUsers = onlineUserService.getOnlineUsers();
		Assert.assertTrue(onlineUsers.size() == 0);
	}
	@Test
	public void getOnline() {
		onlineUserService.setOnline("user1");
		onlineUserService.setOnline("user2");
		onlineUserService.setOnline("user3");

		List<OnlineUser> onlineUsers = onlineUserService.getOnlineUsers();

		OnlineUser onlineUser;
		List<OnlineUser> users = new ArrayList<OnlineUser>();

		onlineUser = new OnlineUser();
		onlineUser.setUsername("user1");
		users.add(onlineUser);

		onlineUser = new OnlineUser();
		onlineUser.setUsername("user2");
		users.add(onlineUser);

		onlineUser = new OnlineUser();
		onlineUser.setUsername("user3");
		users.add(onlineUser);

		Assert.assertEquals(users, onlineUsers);
	}
}
