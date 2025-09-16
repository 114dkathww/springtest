package com.example.demo.dto;

import com.example.demo.entity.Store;
import com.example.demo.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private String name;
    private int price;
    private int stock;
    private String imageFileName;
    private Store store;
	
	
}
