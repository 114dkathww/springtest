package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.ItemRepository;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.Store;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

	private final ItemRepository itemRepository;
	
	private final StoreRepository storeRepository;
	
	@Value("${file.upload-dir}")
	private String uploadDir;

	// 가게 id 값 조회
	public Store findById(Long id) {
		
		return this.storeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 가게 id 를찾을수 없습니다."));
	}
	
	// 아이템 id 값 조회
	public Item findByItemId(Long id) {
		
		return this.itemRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 Item id 를찾을수 없습니다."));
	}
	
	
	// 가게 상품 추가
	@Transactional
	public void createItem(ItemDto itemDto, Long id, MultipartFile file) throws IOException {
		
		Store store = this.storeRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("해당 가게를 찾을수 없습니다."));
        String fileName = createFileName(file.getOriginalFilename());
        file.transferTo(new File(getFullPath(fileName)));
		
		Item i = Item.builder()
				.name(itemDto.getName())
				.price(itemDto.getPrice())
				.stock(itemDto.getStock())
				.imageFileName(fileName)
				.store(store)
				.build();
//		f9717db5-6d1e-4f36-966c-a661e919b867.jpg	-> filename
//		C:/test/uploads/	-> uploadDir
		this.itemRepository.save(i);
	}
	
	// 호출시 업로드 경로와 파일이름을 리턴
    private String getFullPath(String filename) { 
    	return uploadDir + filename; 
    }
    
    // 호출시 String uuid 변수에 랜덤 uuid 를 문자열로 할당 extractExt(originalFilename) 값을 String ext 에 할당
    // 변수 uuid, ext 값을 문자 '.' 과함께 리턴
    private String createFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid + "." + ext;
    }
    
    
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


//    @Transactional
//	public void deleteItem(Long itemId) {
//		this.storeRepository.deleteById(itemId);
//	}
	// 가게 상품 삭제
    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다. id=" + itemId));
        Store store = item.getStore();
        if (store != null) {
            store.getItems().remove(item);  // Store 컬렉션에서 제거
        }
        item.setStore(null);  // 연관관계 끊기
        itemRepository.delete(item);  // 실제 삭제
    }
	
	
}
