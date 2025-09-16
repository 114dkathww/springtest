package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.StoreRepository;
import com.example.demo.dto.StoreDto;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreService {

	private final StoreRepository storeRepository;
	
	// 가게 목록 조회
	public List<Store> findAll() {

		return this.storeRepository.findAll();
	}
	
	// 가게 id 값 조회
	public Store findStoreId(Long id) {
		return this.storeRepository.findById(id)
		.orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을수 없습니다."));
	}
	
	// 가게 생성
	public void createMarket(StoreDto storeDto) {
		
		Store s = Store.builder()
				.name(storeDto.getName())
				.location(storeDto.getLocation())
				.build();
		this.storeRepository.save(s);
	
	}

	// 가게 삭제
	public void deleteStore(Long id) {
		Store s1 = findStoreId(id);
		this.storeRepository.delete(s1);
	}
	
}

