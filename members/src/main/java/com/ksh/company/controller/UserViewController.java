package com.ksh.company.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ksh.company.entity.User;


@Controller
public class UserViewController {
	
	@GetMapping("/home")
	public String home(Model model,@AuthenticationPrincipal User userInfo) throws Exception {
		model.addAttribute("userInfo", userInfo);
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
}
