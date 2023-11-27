package com.ksh.company.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksh.company.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	Optional<MemberEntity> findByEmail(String email);
}
