package com.bit.ww.controller;

import com.bit.ww.MemberMapper.MemberMapper;
import com.bit.ww.dto.Member;
import com.bit.ww.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public abstract class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberMapper memberMapper;

    public List<Member> findall() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return member;
    }

    public String delete(String id) {
        memberMapper.withdrawal(id);
        return id;
    }
}
