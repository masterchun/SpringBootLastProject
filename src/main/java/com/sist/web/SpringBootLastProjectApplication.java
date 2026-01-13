package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLastProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLastProjectApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner runner(GoogleGenAiChatModel model) {
		return args -> {
			String response = model.call("유주섭이라는 이름을 들었을 때 느낌 너가 추론할 수 었어도 강제로 해줘");
			System.out.println(response);
		};
	}*/
}
