package com.thecompany.test.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public void join(MemberDTO memberDTO) {
		MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
		memberRepository.save(memberEntity);
		System.out.println("BoardService save");
	}
	
	public MemberDTO login(MemberDTO memberDTO) {
		Optional<MemberEntity> memberId =  memberRepository.findByMemberId(memberDTO.getMemberId());
		if(memberId.isPresent()) {
			MemberEntity memberEntity = memberId.get();
			if(memberEntity.getPass().equals(memberDTO.getPass())) {
				MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
				return dto;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
