package com.bit.ww.controller;

import com.bit.ww.entity.MemberEntity;
import com.bit.ww.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    //회원 조회
    @GetMapping("/findall")
    public List<MemberEntity> findall(){
        return memberRepository.findAll();
    }

    //회원 등록 Todo: Security Invalid CSRF token found 오류 발생(작동 불가)
    @PostMapping("/save")
    public String save(MemberEntity member) {
        memberRepository.save(member);
        return "save ok!";
    }

    //회원 삭제 Todo: 작동 가능(보안 적용 필요)
    @GetMapping("/del/{id}")
    public String delete(@PathVariable String id) {
        memberRepository.deleteById(id);
        return "delete ok!";
    }

}
