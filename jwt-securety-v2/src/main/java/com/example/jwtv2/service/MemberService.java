package com.example.jwtv2.service;

import com.example.jwtv2.dto.MemberSignInResquestDto;
import com.example.jwtv2.dto.MemberSignUpRequestDto;

public interface MemberService {

	public String signIn(MemberSignInResquestDto requestDto) throws Exception;
	
	public Long signUp(MemberSignUpRequestDto requestDto) throws Exception;
	
}
