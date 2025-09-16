package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.dto.ItemDto;

import com.example.demo.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Controller
public class ItemController {

	private final ItemService itemService;
	
	
	// (관리자용) 선택 지점 상품추가
	@GetMapping("/admin/stores/{id}/items/new")		// item
	public String createItem(@PathVariable("id") Long id, @ModelAttribute("item") ItemDto itemDto, Model model) {	
		this.itemService.findById(id);
		model.addAttribute("storeId", id);
		return "/admin/item";
	}
	
	// (관리자용) 선택 지점 상품추가
	@PostMapping("/admin/stores/{id}/items/new")		
	public String createItem(@PathVariable("id") Long id, @Valid ItemDto itemDto,
			@RequestParam("file") MultipartFile file, 
			BindingResult bindingResult) throws IOException {
		try {
			if(bindingResult.hasErrors()) {
				return "/admin/stores/{id}/items/new";
			}
			this.itemService.createItem(itemDto, id, file);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "redirect:/admin";
	}
	
	// (관리자용) 상품 삭제.
	@PostMapping("admin/items/{itemId}/delete")
	public String deleteItem(@PathVariable("itemId") Long itemId) {
//		model.addAttribute("itemId", itemId);	
		this.itemService.deleteItem(itemId);	
		return "redirect:/admin";
	}
	
	
	
}
