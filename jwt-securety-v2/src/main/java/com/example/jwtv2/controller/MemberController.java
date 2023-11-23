package com.example.jwtv2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtv2.dto.LoginDto;
import com.example.jwtv2.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto dto){
		return ResponseEntity.ok().body(memberService.login(dto.getUsername()));
	}
}
