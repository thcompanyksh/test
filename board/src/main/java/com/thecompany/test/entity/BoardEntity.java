package com.thecompany.test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thecompany.test.dto.BoardDTO;

import lombok.Getter;
import lombok.Setter;

// DB의 테이블 역할을 하는 클래스
// 테이블과 매핑하기위한 클래스 JPA가 관리해줌 
@Entity
@Getter
@Setter
// 엔티티와 맵핑할 테이블을 지정
@Table(name = "board_Table")
public class BoardEntity extends BaseEntity{
	// 특정 속성을 기본키로 설정하는 어노테이션(@Id만있을경우 기본키값 직접 부여해야함)
	@Id
	// 기본값을 DB에 자동으로 생성할수 있음
	@GeneratedValue
	private Long id;
	
	// 객체 필드를 테이블 컬럼과 매핑함
	@Column(length = 20, nullable = false)
	private String boardWriter;
	
	@Column
	private String boardPass;
	
	@Column
	private String boardTitle;
	
	@Column(length = 500)
	private String boardContents;
	
	@Column
	private int boardHits;
	
	@Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

	
	public static BoardEntity toSaveEntity(BoardDTO boarDTO) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setBoardWriter(boarDTO.getBoardWriter());
		boardEntity.setBoardTitle(boarDTO.getBoardTitle());
		boardEntity.setBoardContents(boarDTO.getBoardContents());
		boardEntity.setBoardHits(0);
		boardEntity.setFileAttached(0);
		System.out.println("BoardEntity toSaveEntity");
		return boardEntity;
	}
	
	public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        System.out.println("BoardEntity toUpdateEntity");
        return boardEntity;
	}

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1);
        return boardEntity;
    }
}
