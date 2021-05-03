package com.anwjrrp33.club.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anwjrrp33.club.dto.NoteDTO;
import com.anwjrrp33.club.service.NoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

	private final NoteService noteService; // final

	@PostMapping(value = "")
	public ResponseEntity<Long> register(@RequestBody NoteDTO noteDTO) {
		log.info("---------------------register---------------------");
		log.info("noteDTO");

		Long num = noteService.register(noteDTO);

		return new ResponseEntity<>(num, HttpStatus.OK);
	}
}
