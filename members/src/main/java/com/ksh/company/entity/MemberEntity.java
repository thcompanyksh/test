package com.ksh.company.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ksh.company.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class MemberEntity extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String email;

	@Column
	private String nickname;

	@Column(length = 20, nullable = false)
	private String name;

	@Column
	private String pass;

	public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		return memberEntity;
	}

}