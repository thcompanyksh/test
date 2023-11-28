package com.ksh.company.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksh.company.dto.AddUserRequest;
import com.ksh.company.entity.User;
import com.ksh.company.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Long save(AddUserRequest dto) {
		return userRepository.save(User.builder()
				.email(dto.getEmail())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.build()).getId();
	}
}
