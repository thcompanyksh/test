package com.example.jwtv2.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.example.jwtv2.entity.Member;
import com.example.jwtv2.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberSignUpRequestDto {
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String email;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, message = "닉네임이 너무 짧습니다.")
	private String nickname;

	@NotNull(message = "나이를 입력해주세요")
	@Range(min = 0, max = 150)
	private int age;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
	private String checkedPassword;
	
	private com.example.jwtv2.entity.Role role;
	
	public MemberSignUpRequestDto() {
		
	}
	@Builder
	public Member toEntity() {
		return Member.builder()
				.email(email)
				.nickname(nickname)
				.age(age)
				.password(password)
				.role(Role.USER)
				.build();
	}
}
