package com.thecompany.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
	
	private String email;
	private String nickname;
	private int age;
	
}
