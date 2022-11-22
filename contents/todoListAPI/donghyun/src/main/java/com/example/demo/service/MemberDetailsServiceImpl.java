package com.example.demo.service;

import com.example.demo.repository.JpaMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

    private final JpaMemberRepository memberRepository;

    @Autowired
    public MemberDetailsServiceImpl(JpaMemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[loadUserByUsername] loadUserByUsername 수행. username : {}", username);
        return memberRepository.getByMemberId(username).get();
    }
}
