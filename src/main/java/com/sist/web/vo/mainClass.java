package com.sist.web.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class mainClass {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println(now);
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		
		System.out.println(formatedNow);
	}
}
