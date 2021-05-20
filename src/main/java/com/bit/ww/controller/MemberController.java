package com.bit.ww.controller;

import com.bit.ww.entity.MemberEntity;
import com.bit.ww.mapper.MemberMapper;
import com.bit.ww.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberMapper memberMapper;

    //회원 조회
    @GetMapping("/findall")
    public List<MemberEntity> findall() {
        return memberRepository.findAll();
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
    @PatchMapping ("/update")
    public String delete(@RequestBody MemberEntity member) {
        memberMapper.update(member);
        return "update ok!";
    }

}



