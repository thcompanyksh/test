package com.thecompany.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thecompany.test.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long>{

}
