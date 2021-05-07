package com.anwjrrp33.club.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anwjrrp33.club.security.util.JWTUtil;

public class JWTTests {

	private JWTUtil jwtUtil;

	@BeforeEach
	public void testBefore() {
		System.out.println("testBefore..............");
		jwtUtil = new JWTUtil();
	}

	@Test
	public void testEncode() throws Exception {
		String email = "user95@anwjrrp33.com";
		String str = jwtUtil.generateToken(email);

		Thread.sleep(5000);

		String resultEmail = jwtUtil.validateAndExtract(str);

		System.out.println(resultEmail);
	}
}