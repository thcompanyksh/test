package com.thecompany.test.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public MemberEntity loadUserByUsername(String email) {
		return memberRepository.findByEmail(email)
				.orElseThrow(()-> new IllegalArgumentException(email));
	}
}
