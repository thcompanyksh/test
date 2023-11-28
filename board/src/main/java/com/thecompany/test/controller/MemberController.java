package com.thecompany.test.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/home")
	public String home(Model model,@AuthenticationPrincipal User userInfo) throws Exception {
		model.addAttribute("userInfo", userInfo);
		return "main";
	}
	
	@GetMapping("/login")
	public String login() {
		return "index";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "/member/join";
	}
	
	@PostMapping("/join")
	public String signup(MemberDTO request) {
		memberService.save(request);
		return "redirect:/";
	}
}
