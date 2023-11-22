package com.thecompany.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thecompany.test.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	Optional<MemberEntity> findByMemberId(String memberId);
}
