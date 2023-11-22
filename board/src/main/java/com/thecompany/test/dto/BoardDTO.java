package com.thecompany.test.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.thecompany.test.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long id;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private int boardHits;
	private LocalDateTime boardCreatedTime;
	private LocalDateTime boardUpdatedTime;
    
    private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)
    
    
    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }
    
	public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(boardEntity.getId());
		boardDTO.setBoardWriter(boardEntity.getBoardWriter());
		boardDTO.setBoardTitle(boardEntity.getBoardTitle());
		boardDTO.setBoardContents(boardEntity.getBoardContents());
		boardDTO.setBoardHits(boardEntity.getBoardHits());
		boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
		boardDTO.setBoardUpdatedTime(boardEntity.getCreatedTime());
		if (boardEntity.getFileAttached() == 0) {
            boardDTO.setFileAttached(boardEntity.getFileAttached()); // 0
        } else {
            boardDTO.setFileAttached(boardEntity.getFileAttached()); // 1
            // 파일 이름을 가져가야 함.
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table b where b.id=bf.board_id
            // and where b.id=?
            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());
        }
        System.out.println("Board DTO toBoardDTO");
		return boardDTO;
        }
}
