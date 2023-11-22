package com.thecompany.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thecompany.test.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	// update board_table set board_hits=board_hits+1 where id=?
	// @Query 어노테이션으로 작성된 조회를 제외한 데이터에 변경이 일어나는 메서드를 사용할때 필요
	@Modifying
		// 정의된 필드명은 테이블이 아닌 Entity 클래스와 Entity 속성의 이름
		// @Param 어노테이션을 통해 받을수 있음
		@Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
	void updateHits(@Param("id") Long id);
}
