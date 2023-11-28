package com.ksh.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksh.company.dto.AddUserRequest;
import com.ksh.company.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserApiController {
	private final UserService userService;
	
	@PostMapping("/user")
	public String signup(AddUserRequest request) {
		userService.save(request);
		return "redirect:/login";
	}
	
	
}
