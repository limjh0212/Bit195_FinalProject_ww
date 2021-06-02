package com.bit.ww.service;

import com.bit.ww.entity.MemberEntity;
import com.bit.ww.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<MemberEntity> member = memberRepository.findById(id);
        ArrayList<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(member.get().getRole()));
        return new User(member.get().getId(), member.get().getPw(), role);
    }

    //회원 추가, 수정
    public MemberEntity save(MemberEntity member) {
        return memberRepository.save(member);
    }

    //회원 조회
    public List<MemberEntity> findAll() {
        return memberRepository.findAll();
    }

    //회원 ID 조회
    public Optional<MemberEntity> findbyId(String id) {
        return memberRepository.findById(id);
    }

    //회원 삭제
    public void deleteById(String id) {
        memberRepository.deleteById(id);
    }
}
