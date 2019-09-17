package com.huxiaotian.mapper;

import com.huxiaotian.pojo.HxtUser;
import com.huxiaotian.pojo.HxtUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HxtUserMapper {
    long countByExample(HxtUserExample example);

    int deleteByExample(HxtUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(HxtUser record);

    int insertSelective(HxtUser record);

    List<HxtUser> selectByExample(HxtUserExample example);

    HxtUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") HxtUser record, @Param("example") HxtUserExample example);

    int updateByExample(@Param("record") HxtUser record, @Param("example") HxtUserExample example);

    int updateByPrimaryKeySelective(HxtUser record);

    int updateByPrimaryKey(HxtUser record);
}