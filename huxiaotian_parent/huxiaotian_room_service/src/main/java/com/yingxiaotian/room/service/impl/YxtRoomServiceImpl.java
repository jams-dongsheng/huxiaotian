package com.yingxiaotian.room.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.yingxiaotian.mapper.YxtRoomMapper;
import com.yingxiaotian.pojo.YxtRoom;
import com.yingxiaotian.room.service.YxtRoomService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class YxtRoomServiceImpl implements YxtRoomService {

    @Autowired
    private YxtRoomMapper yxtRoomMapper;

    @Override
    public void add(YxtRoom yxtRoom) {
        yxtRoomMapper.insert(yxtRoom);
    }


    @Override
    public void delete(String roomId) {
        yxtRoomMapper.deleteByPrimaryKey(roomId);
    }

    @Override
    public YxtRoom findOne(String roomId) {
        return yxtRoomMapper.selectByPrimaryKey(roomId);
    }
}

