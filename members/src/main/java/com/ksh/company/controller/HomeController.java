package com.ksh.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

//컨트롤러로 변경
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	// ("/") 주소로 맵핑 Root주소
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
