package com.huxiaotian.service;


import com.yingxiaotian.entity.PageResult;
import com.yingxiaotian.entity.RoomNum;

public interface RoomService {

    /**
     * 入住
     * @param roomId
     */
    public void updateStatusByIn(String roomId);

    /**
     * 退房
     * @param roomId
     */
    public void updateStatusByOut(String roomId);

    /**
     * 查询房间状态和数量
     * @return
     */
    public RoomNum findCount();

    /**
     * 查找空房间
     * @return
     */
    public PageResult findEmptyRoom(int pageNum, int pageSize);

    /**
     * 查询所有房间
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findAllRoom(int pageNum, int pageSize);
}
