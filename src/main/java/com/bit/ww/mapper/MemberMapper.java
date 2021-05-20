package com.bit.ww.mapper;

import com.bit.ww.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

    @Update("UPDATE `ww`.`member` SET pw=#{pw}, email=#{email}, nickname =#{nickname}, gender=#{gender}, " +
            "age=#{age}, img=#{img}, iswithdrawal=#{iswithdrawal}, role=#{role} WHERE  `id`=#{id}")
    public void update(MemberEntity member);

}
