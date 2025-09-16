package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SUserDto {

    private String username;
    private String password;
    private UserRole role;
}
