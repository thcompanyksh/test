package com.thecompany.test.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thecompany.test.dto.MemberDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberEntity extends BaseEntity implements UserDetails{
	@Id
	@GeneratedValue
	@Column(name="id", updatable = false)
	private Long id;

	@Column(name = " email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column
	private String nickname;
	
	@Column
	private int age;
	
	@Column
	private String role;
	
	public MemberEntity(MemberDTO memberDTO, String auth) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setEmail(memberDTO.getEmail());
		memberEntity.setPassword(memberDTO.getPassword());
		memberEntity.setNickname(memberDTO.getNickname());
		memberEntity.setAge(memberDTO.getAge());
	}
	
//	public static MemberEntity update(MemberDTO memberDTO) {
//		MemberEntity memberEntity = new MemberEntity();
//		// memberEntity.setEmail(memberDTO.getEmail());
//		memberEntity.setNickname(memberDTO.getNickname());
//		memberEntity.setAge(memberDTO.getAge());
//		return memberEntity;
//	}
	
	public void update(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return List.of(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
