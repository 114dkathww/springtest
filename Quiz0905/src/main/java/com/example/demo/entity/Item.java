package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stock;
    private String imageFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public Item(String name, int price, int stock, String imageFileName, Store store) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageFileName = imageFileName;
        this.store = store;
    }

    public void update(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

	public void setStore(Object object) {
		// TODO Auto-generated method stub
		
	}
}