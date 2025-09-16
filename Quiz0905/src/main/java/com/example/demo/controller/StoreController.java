package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.demo.service.ItemService;
import com.example.demo.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.StoreDto;
import com.example.demo.entity.Store;
import com.example.demo.service.ItemService;
import com.example.demo.service.StoreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StoreController {

	private final StoreService storeService;
	
	

	// 관리자 페이지 리스트
	@GetMapping("/admin")		// store 
	public String adminPage(Model model) {
		List<Store> list = this.storeService.findAll();
		model.addAttribute("stores", list);
		return "/admin/dashboard";
	}
	
	// 지점 추가
	@GetMapping("/admin/stores/new")		// store 
	public String createMarket(@ModelAttribute("store") StoreDto storeDto) {
		
		return "/admin/store";
	}
	
	// 지점 추가
	@PostMapping("/admin/stores/new")
	public String createMarket(@Valid StoreDto storeDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "/admin/store";
		}
		
		this.storeService.createMarket(storeDto);
		return "redirect:/admin";
	}
	
	// 지점 삭제
	@PostMapping("admin/stores/{id}/delete")
	public String deleteStore(@PathVariable("id") Long id) {
		this.storeService.deleteStore(id);
		return "redirect:/admin";
	}
	
	
	
	
	
}
