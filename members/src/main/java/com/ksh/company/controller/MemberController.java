package com.ksh.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksh.company.dto.MemberDTO;
import com.ksh.company.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@PostMapping("/joinForm")
	public String joinForm() {
		return "member/join";
	}

	@PostMapping("/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		memberService.join(memberDTO);
		return "redirect:/";
	}

	@PostMapping("/main")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.login(memberDTO);
		if (loginResult != null) {
			session.setAttribute("loginId", loginResult.getEmail());
			return "main";
		} else {
			return "index";
		}
	}
}
