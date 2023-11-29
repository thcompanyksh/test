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
	
	public Long update(MemberUpdateDTO memberUpdateDTO) {
		MemberEntity memberEntity = memberRepository.findByEmail(memberUpdateDTO.getEmail()).orElseThrow(()->new IllegalIdentifierException("ss"));
		
		memberEntity.update(memberUpdateDTO.getNickname());
		
		return memberEntity.getId();
	}
	
//	@Transactional
//	public MemberDTO findByEmail(String email) {
//		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmail(email);
//		if (optionalMemberEntity.isPresent()) {
//			MemberEntity memberEntity = optionalMemberEntity.get();
//			MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//			return memberDTO;
//		}else {
//			return null;
//		}
//	}
	
//	public MemberDTO update(MemberDTO memberDTO) throws IOException{
//		MemberEntity memberEntity = MemberEntity.update(memberDTO);
//		memberRepository.save(memberEntity);
//		
//		return memberDTO;
//	}
	
}