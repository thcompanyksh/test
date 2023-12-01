package com.thecompany.test.controller;

import java.util.Objects;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.dto.MemberUpdateDTO;
import com.thecompany.test.entity.MemberEntity;
import com.thecompany.test.repository.MemberRepository;
import com.thecompany.test.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
	@GetMapping("/home")
	public String home(Model model,@AuthenticationPrincipal MemberEntity userInfo) throws Exception {
		model.addAttribute("userInfo",  memberRepository.findById(userInfo.getId()).get());
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
	public String signup(MemberDTO request) throws Exception{
		memberService.save(request);
		return "redirect:/";
	}
	
	@GetMapping("/info")
	// @AuthenticationPrincipal로 데이터 가져 올시 최초 로그인 정보 이기 때문에 DB에서 변경되어도 기존 정보 가지고있음
	public String info(Model model, @AuthenticationPrincipal MemberEntity memberEntity) {
		// 기존 정보 갱신을 위해 DB에서 데이터를 새로 가져옴
		model.addAttribute("info", memberRepository.findById(memberEntity.getId()).get());
		return "/member/info";
	}
	
	@PostMapping("/info")
	public String update(@Valid MemberDTO memberDTO, Errors errors, Model model, Authentication auth) throws Exception{

		model.addAttribute("member", memberDTO);
		memberService.update(memberDTO);
		
		return "redirect:/home";
	}
	
	@GetMapping("/delete")
	public String deleteForm() {
		return "/member/delete";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam String password, Model model, Authentication auth) throws Exception{
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		boolean result = memberService.delete(userDetails.getUsername(), password);
		
		if(result) {
			return "redirect:/";
		}else {
			model.addAttribute("wrongPassowrd", "비밀번호가 맞지 않습니다.");
			return "/member/delete";
		}
	}
	
	@GetMapping("/pass")
	public String passForm() {
		return "/member/pass";
	}
	
	@PostMapping("/pass")
	public String pass(@Valid MemberDTO memberDTO, Model model, Authentication auth) throws Exception{
	    if (!Objects.equals(memberDTO.getNewPassword(), memberDTO.getCheckedPassword())) {
	        model.addAttribute("dto", memberDTO);
	        model.addAttribute("differentPassword", "비밀번호가 같지 않습니다.");
	        return "/member/pass";
	    }
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		Long result = memberService.pass(memberDTO, userDetails.getUsername());
		
	    // 현재 비밀번호가 틀렸을 경우
	    if (result == null) {
	        model.addAttribute("dto", memberDTO);
	        model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
	        return "/member/pass";
	    }

	    return "redirect:/home";
	}
	
}
