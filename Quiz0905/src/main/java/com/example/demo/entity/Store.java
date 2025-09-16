package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;
    
    // mappedBy: Item 엔티티의 'store' 필드가 이 관계의 주인임을 명시
    // cascade: Store가 삭제되면 관련된 Item도 모두 삭제
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    public void update(String name, String location) {
        this.name = name;
        this.location = location;
    }
}