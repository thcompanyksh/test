package com.thecompany.test.service;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.dto.MemberUpdateDTO;
import com.thecompany.test.entity.BoardEntity;
import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.repository.MemberRepository;
import com.thecompany.test.service.MemberService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Long save(MemberDTO dto) throws Exception{
		// 이메일 중복 테스트 로직
		if(memberRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new Exception("이메일이 존재합니다.");
		}
		// 패스워드 체크 로직 추가
		if(!dto.getPassword().equals(dto.getCheckedPassword())) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		return memberRepository.save(MemberEntity.builder()
				.email(dto.getEmail())
				.nickname(dto.getNickname())
				.age(dto.getAge())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.build()).getId();
		}
	
	public Long update(MemberDTO memberDTO) throws Exception {
		//orElseThrow() -> Optional의 인자가 null일 경우 IllegalIdentifierException의 메세지를 반환해 준다.
		MemberEntity memberEntity = memberRepository.findByEmail(memberDTO.getEmail()).orElseThrow(()->new IllegalIdentifierException("ss"));
		
		//passwordEncoder.matches(신규password, 기존 entity password)로 매칭하여 확인
		if(!bCryptPasswordEncoder.matches(memberDTO.getNewPassword(), memberEntity.getPassword())){
			throw new Exception("비밀번호가 일치하지 않습니다.");
		} else {
		memberDTO.setNewPassword(bCryptPasswordEncoder.encode(memberDTO.getNewPassword()));
		memberEntity.update(memberDTO.getNickname());
		memberEntity = memberRepository.save(memberEntity);
		return memberEntity.getId();		
		}
	}
	
	public boolean delete(String email,String password) throws Exception{
		MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow(()->new IllegalIdentifierException("아이디가 없습니다"));
		if(bCryptPasswordEncoder.matches(password, memberEntity.getPassword())){
			memberRepository.delete(memberEntity);
			return true;
		} else {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
	}
	
	public Long pass(MemberDTO memberDTO, String email) throws Exception{
	    MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

	    if (!bCryptPasswordEncoder.matches(memberDTO.getPassword(), memberEntity.getPassword())) {
	    	throw new Exception("비밀번호가 일치하지 않습니다.");
	    } else {
	    	memberDTO.setNewPassword(bCryptPasswordEncoder.encode(memberDTO.getNewPassword()));
	    	memberEntity.updatePass(memberDTO.getNewPassword());
	        memberRepository.save(memberEntity);
	    }
		return memberEntity.getId();

	}
	
}