package com.yingxiaotian.mapper;

import com.huxiaotian.pojo.YxtRoom;
import com.huxiaotian.pojo.YxtRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YxtRoomMapper {
    long countByExample(YxtRoomExample example);

    int deleteByExample(YxtRoomExample example);

    int deleteByPrimaryKey(String roomId);

    int insert(YxtRoom record);

    int insertSelective(YxtRoom record);

    List<YxtRoom> selectByExample(YxtRoomExample example);

    YxtRoom selectByPrimaryKey(String roomId);

    int updateByExampleSelective(@Param("record") YxtRoom record, @Param("example") YxtRoomExample example);

    int updateByExample(@Param("record") YxtRoom record, @Param("example") YxtRoomExample example);

    int updateByPrimaryKeySelective(YxtRoom record);

    int updateByPrimaryKey(YxtRoom record);
}