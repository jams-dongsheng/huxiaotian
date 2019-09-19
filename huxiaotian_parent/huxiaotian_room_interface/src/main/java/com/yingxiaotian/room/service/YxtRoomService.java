package com.yingxiaotian.room.service;


import com.yingxiaotian.pojo.YxtRoom;

public interface YxtRoomService {

    public void add(YxtRoom yxtRoom);


    void delete(String roomId);


    YxtRoom findOne(String roomId);
}
