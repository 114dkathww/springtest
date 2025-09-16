package com.example.demo.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SUser;
import com.example.demo.repository.SUserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SUserRepository suserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SUser suser = suserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        // TODO: 사용자의 역할(Role) 정보를 GrantedAuthority로 변환하여 UserDetails 객체를 생성하세요.
        return new org.springframework.security.core.userdetails.User(
                suser.getUsername(),
                suser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(suser.getRole().getValue()))
        );
    }
}
