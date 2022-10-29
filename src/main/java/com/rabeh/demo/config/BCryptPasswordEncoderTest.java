package com.rabeh.demo.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		String encodedPWD = bpe.encode("admin");
		System.out.println(encodedPWD);
	}

}
