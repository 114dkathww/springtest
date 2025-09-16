package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.SUserDto;
import com.example.demo.entity.SUser;
import com.example.demo.service.ItemService;
import com.example.demo.service.SUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/users")		
@RequiredArgsConstructor
@Controller
public class SUserController {

	private final SUserService sUserService;

	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "/users/login";
	}
	
	// 회원가입
	@GetMapping("/signup")
	public String signup(@ModelAttribute("user") SUserDto suserDto) {		// user 객체명
		
		return "/users/signup";
	}
	// 회원가입
	@PostMapping("/signup")
	public String signup(@Valid SUserDto suserDto, BindingResult bindingResult) {		
		
		if(bindingResult.hasErrors()) {
			return "/users/signup";
		}
		
		this.sUserService.userSignup(suserDto);
		return "redirect:/";
	}
	
	
}
