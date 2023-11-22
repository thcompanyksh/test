package com.thecompany.test.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thecompany.test.dto.BoardDTO;
import com.thecompany.test.service.BoardService;

import lombok.RequiredArgsConstructor;

//Controller로 변경
@Controller
// 필드의 생성자를 자동으로 만들어주는 어노테이션
@RequiredArgsConstructor
// 주소를 board는 고정적으로 지정
//@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/write")
	public String writeForm() {
		System.out.println("controller writeForm");
		return "board/save";
	}
	
	@PostMapping("/write")
	public String write(@ModelAttribute BoardDTO boardDTO) throws IOException{
		boardService.write(boardDTO);
		return "redirect:/main/paging";
	}
	
	// 조회수 1씩 증가한다
	@GetMapping("/board/{id}")
	public String findById(@PathVariable long id, Model model) {
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		System.out.println("controller list(detail)");
		return "board/detail";
	}
	
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("boardUpdate", boardDTO);
		System.out.println("controller updateForm");
		return "board/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        System.out.println("controller update");
		return "board/detail";
	}
	
	@GetMapping("/main")
	public String findAll(@PageableDefault(page=1) Pageable pageable, Model model) {
		// DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
		pageable.getPageNumber();
		Page<BoardDTO> boardList = boardService.paging(pageable);
		int blockLimit = 3;
		int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1 ) * blockLimit +1; //1 4 7 10 ~
		int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit -1 : boardList.getTotalPages();
		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		System.out.println("controller list(findAll)");
		return "main";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		boardService.delete(id);
		System.out.println("Controller delete");
		return "redirect:/";
	}
	
	@GetMapping("/main/paging")
	public String paging(@PageableDefault(page=1) Pageable pageable, Model model) {
		pageable.getPageNumber();
		Page<BoardDTO> boardList = boardService.paging(pageable);
		int blockLimit = 3;
		int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1 ) * blockLimit +1; //1 4 7 10 ~
		int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit -1 : boardList.getTotalPages();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "paging";
	}
	
	
}
