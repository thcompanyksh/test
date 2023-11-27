package com.ksh.company.dto;

import com.ksh.company.entity.MemberEntity;

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
		private String email;
		private String nickname;
		private String name;
		private String pass;

		public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(memberEntity.getId());
			memberDTO.setEmail(memberEntity.getEmail());
			memberDTO.setPass(memberEntity.getPass());
			memberDTO.setName(memberEntity.getName());
			return memberDTO;
		}
	}

