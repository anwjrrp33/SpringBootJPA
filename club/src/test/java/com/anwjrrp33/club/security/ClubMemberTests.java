package com.anwjrrp33.club.security;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.anwjrrp33.club.entity.ClubMember;
import com.anwjrrp33.club.entity.ClubMemberRole;
import com.anwjrrp33.club.repository.ClubMemberRepository;

@SpringBootTest
public class ClubMemberTests {

	@Autowired
	private ClubMemberRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void insertDummies() {
		// 1-80까지는 USER만 지정
		// 81-90까지는 USER, MANAGER
		// 91-100까지는 USER, MANAGER, ADMIN
		IntStream.rangeClosed(1, 100).forEach(i -> {
			ClubMember clubMember = ClubMember.builder()
				.email("user" + i + "@anwjrrp33.com")
				.name("사용자" + i)
				.fromSocial(false)
				.password(passwordEncoder.encode("1111"))
				.build();
			// default role
			clubMember.addMemberRole(ClubMemberRole.USER);
			if(i > 80) {
				clubMember.addMemberRole(ClubMemberRole.MANAGER);
			}
			if(i > 90) {
				clubMember.addMemberRole(ClubMemberRole.ADMIN);
			}
			repository.save(clubMember);
		});
	}
}
