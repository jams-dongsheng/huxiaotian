package com.yingxiaotian.mapper;

import com.huxiaotian.pojo.YxtUser;
import com.huxiaotian.pojo.YxtUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YxtUserMapper {
    long countByExample(YxtUserExample example);

    int deleteByExample(YxtUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(YxtUser record);

    int insertSelective(YxtUser record);

    List<YxtUser> selectByExample(YxtUserExample example);

    YxtUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") YxtUser record, @Param("example") YxtUserExample example);

    int updateByExample(@Param("record") YxtUser record, @Param("example") YxtUserExample example);

    int updateByPrimaryKeySelective(YxtUser record);

    int updateByPrimaryKey(YxtUser record);
}