package com.tda.presentation.client.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class NotInitializedException extends Exception implements
		IsSerializable {
	
	private String error;
	
	public NotInitializedException(){
	}
	
	public NotInitializedException(String clazz){
		error = clazz + ": ";
	}
	
	public String getMessage(){
		return error + "Got to initialize first";
	}
}
