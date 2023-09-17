package com.springsecurity.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {
	
	private GenerateCodeUtil() {}
	
	public static String generateCode() {
		try {
			var random = SecureRandom.getInstanceStrong();
			//1000 ~ 9999 사이 난수
			
			return String.valueOf(random.nextInt(9000) + 1000);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Problem when generating the random code.");
		}
	}
}
