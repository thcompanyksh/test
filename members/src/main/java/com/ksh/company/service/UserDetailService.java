package com.ksh.company.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ksh.company.entity.User;
import com.ksh.company.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService{
	
	private final UserRepository memberRepository;
	
	@Override
	public User loadUserByUsername(String email) {
		return memberRepository.findByEmail(email)
				.orElseThrow(()-> new IllegalArgumentException(email));
	}
}
