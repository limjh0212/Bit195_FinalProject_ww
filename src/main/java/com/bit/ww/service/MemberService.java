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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    //회원 가입 확인
    public boolean checkId(String id) {
        return memberRepository.existsById(id);
    }

    //회원 삭제
    public void deleteById(String id) {
        memberRepository.deleteById(id);
    }

    //회원 정보
    // 총 회원 수
    public int cntMembers(){
        return memberRepository.countAllByIdIsNotNull();
    }
    // 성별, 연령별 회원 수
    public int cntGenderAndAge(boolean gender, int start, int end){
        return memberRepository.countAllByGenderAndIswithdrawalIsFalseAndAgeBetween(gender, start, end);
    }
    // 총 회원 중 가입탈퇴 수
    public int cntWthdrFalse(){
        return memberRepository.countAllByIswithdrawalIsFalse();
    }
    public int cntWthdrTrue(){
        return memberRepository.countAllByIswithdrawalIsTrue();
    }
    // 일주일 간 일일 가입 탈퇴 수
    public List cntToday(){
        List<Object> info = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
                LocalDate date = LocalDate.now().minusDays(i);
                LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
                LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
                //idx 붙이면 날짜 순, 안붙이면 최신순
                info.add(date);
                info.add(memberRepository.countAllByIswithdrawalIsFalseAndRegdateIsBetween(start, end));
                info.add(memberRepository.countAllByIswithdrawalIsTrueAndWthdrdateIsBetween(start, end));
                System.out.println(info);
        }
        System.out.println(info);
        return info;
    }
}
