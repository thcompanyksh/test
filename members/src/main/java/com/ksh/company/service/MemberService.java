package com.ksh.company.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ksh.company.dto.MemberDTO;
import com.ksh.company.entity.MemberEntity;
import com.ksh.company.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService{

	private final MemberRepository memberRepository;

	public void join(MemberDTO memberDTO) {
		MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
		memberRepository.save(memberEntity);
		System.out.println("BoardService save");
	}

	public MemberDTO login(MemberDTO memberDTO) {
		Optional<MemberEntity> memberId =  memberRepository.findByEmail(memberDTO.getEmail());
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
