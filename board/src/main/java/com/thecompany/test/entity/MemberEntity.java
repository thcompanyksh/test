package com.thecompany.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thecompany.test.dto.MemberDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class MemberEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String memberId;
	
	@Column
	private String pass;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@Column
	private int age;
	
	@Column
	private Long phone;
	
	@Column
	private String addr;
	
	@Column
	private String email;
	
	
	public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMemberId(memberDTO.getMemberId());
		memberEntity.setPass(memberDTO.getPass());
		memberEntity.setName(memberDTO.getName());
		memberEntity.setAge(memberDTO.getAge());
		memberEntity.setPhone(memberDTO.getPhone());
		memberEntity.setAddr(memberDTO.getAddr());
		memberEntity.setEmail(memberDTO.getEmail());
		System.out.println("memberEntity toSaveEntity");
		return memberEntity;
	}
}
