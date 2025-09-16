package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Store;
import com.example.demo.service.ItemService;
import com.example.demo.service.StoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NormalUserController {

	private final StoreService storeService;
	
	private final ItemService itemService;
	
	
	// 기본 페이지
	@GetMapping("/")
	public String basicPage(Model model) {		// user 객체명
		model.addAttribute("stores", this.storeService.findAll()) ;
		return "/stores/list";
	}
	
	// 상점 디테일 페이지
	@GetMapping("stores/{id}")
	public String storedetail(@PathVariable("id") Long id, Model model) {		// store	
		model.addAttribute("store", this.storeService.findStoreId(id)) ;
		return "/stores/detail";
	}
	
	
}
