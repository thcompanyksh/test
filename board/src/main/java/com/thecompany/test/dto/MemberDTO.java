package com.thecompany.test.dto;

import com.thecompany.test.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private Long id;
	private String memberId;
	private String pass;
	private String name;
	private int age;
	private Long phone;
	private String addr;
	private String email;
	
	public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(memberEntity.getId());
		memberDTO.setMemberId(memberEntity.getMemberId());
		memberDTO.setPass(memberEntity.getPass());
		memberDTO.setName(memberEntity.getName());
		memberDTO.setAge(memberEntity.getAge());
		memberDTO.setPhone(memberEntity.getPhone());
		memberDTO.setAddr(memberEntity.getAddr());
		memberDTO.setEmail(memberEntity.getEmail());
		return memberDTO;
	}
}
