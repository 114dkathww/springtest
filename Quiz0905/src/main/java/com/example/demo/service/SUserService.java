package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SUserDto;
import com.example.demo.entity.SUser;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.SUserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SUserService {

	private final PasswordEncoder passwordEncoder;
	
	private final SUserRepository sUserRepository;

	public void userSignup(SUserDto suserDto) {
		SUser s = SUser.builder()
		.username(suserDto.getUsername())
		.password(passwordEncoder.encode(suserDto.getPassword()))
		.role(UserRole.USER)
		.build();
		this.sUserRepository.save(s);
	}
}
