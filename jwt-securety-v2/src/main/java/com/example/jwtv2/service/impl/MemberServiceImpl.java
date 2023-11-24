package com.example.jwtv2.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtv2.dto.MemberSignInResquestDto;
import com.example.jwtv2.dto.MemberSignUpRequestDto;
import com.example.jwtv2.entity.Member;
import com.example.jwtv2.jwt.JwtUtil;
import com.example.jwtv2.repository.MemberRepository;
import com.example.jwtv2.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Long expiredMs = 1000 * 60 * 60l;
	
	
	@Override
	@Transactional
	public Long signUp(MemberSignUpRequestDto requestDto) throws Exception{
		if(memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
			throw new Exception("이미 존재하는 이메일입니다.");
		}
		
		if(!requestDto.getPassword().equals(requestDto.getCheckedPassword())){
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		Member member = memberRepository.save(requestDto.toEntity());
		member.encodePassword(passwordEncoder);
		
		member.addUserAuthority();
		return member.getId();
	}
	
	public String signIn(MemberSignInResquestDto requestDto) {
		
		Member member = memberRepository.findByEmail(requestDto.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));
		if(!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
			throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
		}
		
		return JwtUtil.createJwt(member.getNickname(), secretKey, expiredMs);
	}
}
