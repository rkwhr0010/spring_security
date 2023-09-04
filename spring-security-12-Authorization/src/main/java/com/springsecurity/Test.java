package com.springsecurity;

import java.time.LocalTime;

public class Test {
	public static void main(String[] args) {
		
		LocalTime now = LocalTime.now();
		System.out.println(now);
		System.out.println(now.isAfter(LocalTime.of(9, 0)));
		System.out.println(now.isBefore(LocalTime.of(18, 0)));
		
	}
}
