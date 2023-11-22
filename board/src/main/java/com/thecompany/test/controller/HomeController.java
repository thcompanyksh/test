package com.thecompany.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.thecompany.test.service.BoardService;

import lombok.RequiredArgsConstructor;

//컨트롤러로 변경
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final BoardService boardService;
	
	// ("/") 주소로 맵핑 Root주소
	@GetMapping("/")
	public String index() {
//		List<BoardDTO> boardDTOList = boardService.findAll();
//		model.addAttribute("boardList",boardDTOList);
//		System.out.println("homeController list(findAll)");
		return "index";
	}
}
