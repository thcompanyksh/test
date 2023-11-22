package com.thecompany.test.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thecompany.test.dto.BoardDTO;
import com.thecompany.test.entity.BoardEntity;
import com.thecompany.test.entity.BoardFileEntity;
import com.thecompany.test.repository.BoardFileRepository;
import com.thecompany.test.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

// DTO -> Entity
// Entity -> DTO

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final BoardFileRepository boardFileRepository;
	
	public void write(BoardDTO boardDTO) throws IOException {
		if(boardDTO.getBoardFile().isEmpty()) {
		BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
		boardRepository.save(boardEntity);
		} else {
			MultipartFile boardFile = boardDTO.getBoardFile();
			String originalFilename = boardFile.getOriginalFilename();
			String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
			String savePath = "C:/springboot_img/" + storedFileName;
			boardFile.transferTo(new File(savePath)); // 5.
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(savedId).get();
            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
            System.out.println("BoardService save"+boardFileEntity);
		}
	}
	
	@Transactional
	public List<BoardDTO> findAll() {
		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for(BoardEntity boardEntity: boardEntityList) {
			boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
		}
		System.out.println("BoardService findAll");
		return boardDTOList;
	}
	
	@Transactional
	public void updateHits(long id) {
		boardRepository.updateHits(id);
		System.out.println("BoardService updateHits");
	}
	
	@Transactional
	public BoardDTO findById(long id) {
		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
		if (optionalBoardEntity.isPresent()){
			BoardEntity boardEntity = optionalBoardEntity.get();
			BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
			System.out.println("BoardService findById");
			return boardDTO;
		} else {
			return null;
		}
	}
	
	public BoardDTO update(BoardDTO boardDTO) {
		BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
		boardRepository.save(boardEntity);
		System.out.println("BoardService update");
		return findById(boardDTO.getId());
	}
	
	public void delete(long id) {
		boardRepository.deleteById(id);
	}
	
	public Page<BoardDTO> paging(Pageable pageable){
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 3;
		
		Page<BoardEntity> boardEntities =
				boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부
        
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
	}

}
