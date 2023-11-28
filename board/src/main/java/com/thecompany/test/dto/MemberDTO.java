package com.thecompany.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberDTO {
	
	private String email;
	private String password;
	private String nickname;
	private int age;
	//private String checkedPassword;
	private String role;
	
}