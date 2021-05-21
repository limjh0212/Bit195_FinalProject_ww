package com.bit.ww.controller;

import com.bit.ww.entity.MemberEntity;
import com.bit.ww.mapper.MemberMapper;
import com.bit.ww.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberMapper memberMapper;

    //회원 전체 조회
    @GetMapping("/findall")
    public List<MemberEntity> findall() {
        return memberRepository.findAll();
    }

    //회원 ID 조회
    @GetMapping("/findbyid/{id}")
    public Optional<MemberEntity> findbyid(@PathVariable String id) {
//        return memberMapper.findById(id);
        return memberRepository.findById(id);
    }


    //회원 등록
    @PostMapping("/save")
    public String save(@RequestBody MemberEntity member) {
        memberRepository.save(member);
        return "save ok!";
    }

    //회원 삭제
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        memberRepository.deleteById(id);
        return "delete ok!";
    }

    //회원 수정
    @PatchMapping("/update")
    public String delete(@RequestBody MemberEntity member) {
        memberMapper.update(member);
        return "update ok!";
    }

}



