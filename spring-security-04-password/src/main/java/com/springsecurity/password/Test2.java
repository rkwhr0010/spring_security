package com.springsecurity.password;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class Test2 {
	public static void main(String[] args) {
		stringBasedEncrption();
		System.out.println();
		byteBasedEncrption();
	}

	private static void stringBasedEncrption() {
		System.out.println("문자열 기반 암호화");
		StringKeyGenerator keyGenerator = KeyGenerators.string();
		
		String password = "tiger";
		String solt = keyGenerator.generateKey();
		
		//암호화 하지 않음
//		TextEncryptor encryptor = Encryptors.noOpText();
		//기본
//		TextEncryptor encryptor = Encryptors.text(password, solt);
		//더 강력
		TextEncryptor encryptor = Encryptors.delux(password, solt);
		
		String plainText = "민감한 데이터";
		//encrypt() 매 실행 마다 값이 다르다. 내부적으로 난수를 사용한다.
		String cypherText1 = encryptor.encrypt(plainText);
		String cypherText2 = encryptor.encrypt(plainText);
		
		System.out.println("plainText = " + plainText);
		System.out.println("cypherText1 = " + cypherText1);
		System.out.println("cypherText2 = " + cypherText2);
		System.out.println(encryptor.decrypt(cypherText1));
		System.out.println(Objects.equals(plainText, encryptor.decrypt(cypherText1)));
	}
	private static void byteBasedEncrption() {
		System.out.println("바이트 기반 암호화");
		StringKeyGenerator keyGenerator = KeyGenerators.string();
		
		String password = "민감한 데이터";
		String solt = keyGenerator.generateKey();
		
		//기본
//		BytesEncryptor encryptor = Encryptors.standard(password, solt);
		//더 강력
		BytesEncryptor encryptor = Encryptors.stronger(password, solt);
		
		byte[] plainByte = password.getBytes(StandardCharsets.UTF_8);
		//encrypt() 매 실행 마다 값이 다르다. 내부적으로 난수를 사용한다.
		byte[] cypherByte1 = encryptor.encrypt(plainByte);
		byte[] cypherByte2 = encryptor.encrypt(plainByte);
		
		System.out.println("plainByte" + Arrays.toString(plainByte));
		System.out.println("cypherByte1 = " + Arrays.toString(cypherByte1));
		System.out.println("cypherByte2 = " + Arrays.toString(cypherByte2));
		System.out.println(new String(encryptor.decrypt(cypherByte1), StandardCharsets.UTF_8));
		System.out.println(Objects.equals(plainByte, encryptor.decrypt(cypherByte1)));
	}
}
