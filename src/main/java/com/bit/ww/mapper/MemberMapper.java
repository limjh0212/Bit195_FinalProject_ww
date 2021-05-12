package com.bit.ww.mapper;

import com.bit.ww.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

    //회원 수정
    @Update("UPDATE member set pw=#{pw}, email=#{email}, nickName=#{nickName} where id=#{id}")
    public String memberUpdate(Member member);

    //회원 삭제
    @Update("UPDATE member set isWithdrawal=1")
    public String withdrawal(String id);
}
