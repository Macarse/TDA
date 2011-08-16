package com.tda.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
	public static String getHash(String password) {

		if (password == null || password == "")
			return null;

		byte[] bytesOfMessage = { 0 };
		try {
			bytesOfMessage = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] thedigest = md.digest(bytesOfMessage);

		String hashedString = null;
		try {
			hashedString = getHexString(thedigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashedString;
	}

	private static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
}
