package com.tda.service;

public class GreetingServiceImpl implements GreetingService {

	public String greetServer(String name) throws IllegalArgumentException {
		return "Hola " + name;
	}
}
