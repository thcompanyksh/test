package com.ksh.company.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// 매핑,엔티티,테이블과 전혀 관계없이 매핑정보만 제공
@MappedSuperclass
// 커스컴 콜백후 AuditiongEntityListener.class로 인자를 넘김
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
	// insert 쿼리가 발생할때 현재시간을 값으로 넣어서 실행
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	// update 쿼리가 발생할때 발생시간을 값을 넣어서 실행
	@Column(insertable = false)
	private LocalDateTime updatedTime;
}
