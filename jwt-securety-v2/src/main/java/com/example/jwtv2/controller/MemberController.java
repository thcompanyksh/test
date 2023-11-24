package com.example.jwtv2.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtv2.dto.LoginDto;
import com.example.jwtv2.dto.MemberSignInResquestDto;
import com.example.jwtv2.dto.MemberSignUpRequestDto;
import com.example.jwtv2.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody MemberSignInResquestDto request) throws Exception{
		return ResponseEntity.ok().body(memberService.signIn(request));
	}
	
	@PostMapping("/join")
	@ResponseStatus(HttpStatus.OK)
	public long join(@Valid @RequestBody MemberSignUpRequestDto request) throws Exception{
		return memberService.signUp(request);
	}
}
