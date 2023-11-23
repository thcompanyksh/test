package com.example.jwtv2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.jwtv2.jwt.JwtUtil;

@Service
public class MemberService {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Long expiredMs = 1000 * 60 * 60l;
	
	public String login(String username) {
		return JwtUtil.createJwt(username, secretKey, expiredMs);
	}
}
