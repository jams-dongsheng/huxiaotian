package com.huxiaotian.room.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.huxiaotian.service.RoomService;
import com.yingxiaotian.entity.PageResult;
import com.yingxiaotian.entity.RoomNum;
import com.yingxiaotian.group.Room;
import com.yingxiaotian.mapper.YxtRoomMapper;
import com.yingxiaotian.mapper.YxtUserMapper;
import com.yingxiaotian.pojo.YxtRoom;
import com.yingxiaotian.pojo.YxtRoomExample;
import com.yingxiaotian.pojo.YxtUser;
import com.yingxiaotian.pojo.YxtUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private YxtRoomMapper YxtRoomMapper;

    @Autowired
    private YxtUserMapper yxtUserMapper;

    /**
     * 入住修改房间状态
     * @param roomId
     */
    @Override
    public void updateStatusByIn(String roomId) {
        YxtRoom YxtRoom = YxtRoomMapper.selectByPrimaryKey(roomId);
        YxtRoom.setRoomStatus("2");//1为空房间；2为满房间
        YxtRoomMapper.updateByPrimaryKey(YxtRoom);
    }

    /**
     * 退房
     * @param userId
     */
    @Override
    public void updateStatusByOut(String userId) {
        YxtUser yxtUser = yxtUserMapper.selectByPrimaryKey(userId);
        YxtRoom yxtRoom = YxtRoomMapper.selectByPrimaryKey(yxtUser.getUserRoomId());
        yxtRoom.setRoomStatus("1");//1为空房间；2为满房间
        YxtRoomMapper.updateByPrimaryKey(yxtRoom);
    }

    /**
     * 统计房间状态及数量
     * @return
     */
    @Override
    public RoomNum findCount() {
        RoomNum roomNum = new RoomNum();
        //查询所有房间
        long total = YxtRoomMapper.countByExample(null);
        roomNum.setTotalRoom(total);
        //查询入住房间
        YxtRoomExample example = new YxtRoomExample();
        YxtRoomExample.Criteria criteria = example.createCriteria();
        criteria.andRoomStatusEqualTo("2");//1为空房间；2为满房间
        long fullRoom = YxtRoomMapper.countByExample(example);
        roomNum.setFullRoom(fullRoom);
        roomNum.setEmptyRoom(total-fullRoom);
        return roomNum;
    }

    /**
     * 查找空房间
     * @return
     */
    @Override
    public PageResult findEmptyRoom(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        YxtRoomExample example = new YxtRoomExample();
        YxtRoomExample.Criteria criteria = example.createCriteria();
        criteria.andRoomStatusEqualTo("1");//1为空房间；2为满房间
        Page<YxtRoom> page = (Page<YxtRoom>) YxtRoomMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 查找所有房间
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findAllRoom(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<YxtRoom> page = (Page<YxtRoom>)YxtRoomMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 添加房间
     * @param yxtRoom
     */
    @Override
    public void add(YxtRoom yxtRoom) {
        YxtRoomMapper.insert(yxtRoom);
    }

    /**
     * 删除房间
     * @param roomId
     */
    @Override
    public void delete(String roomId) {
        YxtRoomMapper.deleteByPrimaryKey(roomId);
    }

    /**
     * 回显
     * @param roomId
     * @return
     */
    @Override
    public YxtRoom findOne(String roomId) {
        return YxtRoomMapper.selectByPrimaryKey(roomId);
    }

    /**
     * 修改房间
     * @param yxtRoom
     */
    @Override
    public void update(YxtRoom yxtRoom) {
        YxtRoomMapper.updateByPrimaryKey(yxtRoom);
    }

    /**
     * 封装返回对象
     * @return
     */
    private List<Room> createAll(){
        List<Room> rooms = new ArrayList<>();
        List<YxtRoom> yxtRooms = YxtRoomMapper.selectByExample(null);
        for (YxtRoom yxtRoom : yxtRooms) {
            Room room = new Room();
            room.setYxtRoom(yxtRoom);
            if(yxtRoom.getRoomStatus().equals("1")){//1为空房间；2为满房间
                continue;
            }
            YxtUserExample example = new YxtUserExample();
            YxtUserExample.Criteria criteria = example.createCriteria();
            criteria.andUserRoomIdEqualTo(yxtRoom.getRoomId());
            criteria.andUserStatusEqualTo("0");//0为订单未结束；1为订单结束
            List<YxtUser> yxtUsers = yxtUserMapper.selectByExample(example);
            if(yxtUsers != null && yxtUsers.size() > 0){
                YxtUser yxtUser = yxtUsers.get(0);
                room.setYxtUser(yxtUser);
            }
            rooms.add(room);
        }
        return rooms;
    }
}
