package com.bit.ww.repository;

import com.bit.ww.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    MemberEntity findOneById(String id);
    // 총 회원수
    int countAllByIdIsNotNull();
    // 성별, 연령별 회원 수
    int countAllByGenderAndIswithdrawalIsFalseAndAgeBetween(boolean gender, int start, int end);
    // 총 회원 중 가입탈퇴 수
    int countAllByIswithdrawalIsFalse();
    int countAllByIswithdrawalIsTrue();
    // 일일 가입 탈퇴 수
    int countAllByIswithdrawalIsFalseAndRegdateIsBetween(LocalDateTime start, LocalDateTime end);
    int countAllByIswithdrawalIsTrueAndWthdrdateIsBetween(LocalDateTime start,LocalDateTime end);
}
