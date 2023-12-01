package com.thecompany.test.dto;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.User;

import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private int age;
	private String checkedPassword;
	private String newPassword;
	private Role role;
	
	
	public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(memberEntity.getId());
		memberDTO.setEmail(memberEntity.getEmail());
		memberDTO.setPassword(memberEntity.getPassword());
		memberDTO.setNickname(memberEntity.getNickname());
		memberDTO.setAge(memberEntity.getAge());
		
		return memberDTO;
		
	}
}