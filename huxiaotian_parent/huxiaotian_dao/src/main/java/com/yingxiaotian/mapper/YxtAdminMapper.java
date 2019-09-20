package com.yingxiaotian.mapper;

import com.yingxiaotian.pojo.YxtAdmin;
import com.yingxiaotian.pojo.YxtAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxtAdminMapper {
    long countByExample(YxtAdminExample example);

    int deleteByExample(YxtAdminExample example);

    int deleteByPrimaryKey(String username);

    int insert(YxtAdmin record);

    int insertSelective(YxtAdmin record);

    List<YxtAdmin> selectByExample(YxtAdminExample example);

    YxtAdmin selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") YxtAdmin record, @Param("example") YxtAdminExample example);

    int updateByExample(@Param("record") YxtAdmin record, @Param("example") YxtAdminExample example);

    int updateByPrimaryKeySelective(YxtAdmin record);

    int updateByPrimaryKey(YxtAdmin record);
}