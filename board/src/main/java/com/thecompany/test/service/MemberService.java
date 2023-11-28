package com.thecompany.test.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.repository.MemberRepository;
import com.thecompany.test.service.MemberService;



@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Long save(MemberDTO dto) {
		return memberRepository.save(MemberEntity.builder()
				.email(dto.getEmail())
				.nickname(dto.getNickname())
				.age(dto.getAge())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.build()).getId();
	}
}