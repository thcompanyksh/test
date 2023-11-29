package com.thecompany.test.controller;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.dto.MemberUpdateDTO;
import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/home")
	public String home(Model model,@AuthenticationPrincipal MemberEntity userInfo) throws Exception {
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
	
	@GetMapping("/info")
	public String info(Model model, @AuthenticationPrincipal MemberEntity memberEntity) {
		model.addAttribute("info", memberEntity);
		return "/member/info";
	}
	
	@PostMapping("/info")
	public String update(@Valid MemberUpdateDTO memberUpdateDTO, Errors errors, Model model, Authentication auth){
//		if(errors.hasErrors()) {
			model.addAttribute("member", memberUpdateDTO);
//			return "/member/info";
//		}
		
		memberService.update(memberUpdateDTO);
		
		return "redirect:/home";
	}
	
}
