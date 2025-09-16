package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto {
    private String name;
    private String location;
    private List<ItemDto> items = new ArrayList<>();
}
