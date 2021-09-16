package com.kosmo.funfunhaejwo.security.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j  @Transactional
public class LoginUserDetailService implements UserDetailsService {

    private final MemberRepo memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsService loadUserByUsername");
        Member member = memberRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Member not found in the MemberRepo"));
        if (member == null) {
            log.info("# Member not found in the database");
            throw new UsernameNotFoundException("Member not found in the database");
        } else {
            log.info("# Member found in the database {}", username);
        }
        log.info("member {}", member);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().getKey()));
        return new User(member.getEmail(), member.getPassword(), authorities);
    }

}
