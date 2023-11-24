package com.thecompany.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thecompany.test.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	 Optional<Member> findByEmail(String email);
}
