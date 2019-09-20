package com.huxiaotian.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.huxiaotian.service.UserService;
import com.huxiaotian.util.IdWorker;
import com.yingxiaotian.entity.PageResult;
import com.yingxiaotian.group.Room;
import com.yingxiaotian.mapper.YxtRoomMapper;
import com.yingxiaotian.mapper.YxtUserMapper;
import com.yingxiaotian.pojo.YxtRoom;
import com.yingxiaotian.pojo.YxtUser;
import com.yingxiaotian.pojo.YxtUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private YxtUserMapper yxtUserMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private YxtRoomMapper yxtRoomMapper;

    /**
     * 通过住户姓名或房间号查询用户信息
     * @return
     */
    @Override
    public Room findByNameOrId(String search) {
        Room room = new Room();
        //根据住户姓名查找
        YxtUserExample example = new YxtUserExample();
        YxtUserExample.Criteria criteria1 = example.createCriteria();
        criteria1.andNameEqualTo(search);
        criteria1.andUserStatusEqualTo("0");//0为订单未结束；1为订单结束
        List<YxtUser> yxtUsers = yxtUserMapper.selectByExample(example);
        if(yxtUsers != null && yxtUsers.size() >0){
            room.setYxtUser(yxtUsers.get(0));
            String userRoomId = yxtUsers.get(0).getUserRoomId();
            YxtRoom yxtRoom = yxtRoomMapper.selectByPrimaryKey(userRoomId);
            room.setYxtRoom(yxtRoom);
            return room;
        }
        //如果未查到，再根据房间号查询
        YxtRoom yxtRoom = yxtRoomMapper.selectByPrimaryKey(search);
        if(yxtRoom != null){
            room.setYxtRoom(yxtRoom);
            //判断是否有人入住
            if(yxtRoom.getRoomStatus().equals("2")){//1为空房间，2为满房间
                //如果入住,查询订单表
                YxtUserExample example1 = new YxtUserExample();
                YxtUserExample.Criteria criteria = example1.createCriteria();
                criteria.andUserRoomIdEqualTo(yxtRoom.getRoomId());
                criteria.andUserStatusEqualTo("0");//0为订单未结束；1为订单结束
                List<YxtUser> yxtUsers1 = yxtUserMapper.selectByExample(example1);
                if(yxtUsers1 != null && yxtUsers1.size() >0){
                    room.setYxtUser(yxtUsers1.get(0));
                }
            }
            return room;
        }
        return null;
    }

    /**
     * 入住，添加订单
     * @param user
     * @return
     */
    @Override
    public void add(YxtUser user) {
        YxtUser yxtUser = new YxtUser();
        yxtUser.setUserId(String.valueOf(idWorker.nextId()));
        yxtUser.setName(user.getName());//姓名
        yxtUser.setGender(user.getGender());//性别
        yxtUser.setNationality(user.getNationality());//国籍
        yxtUser.setDeposit(user.getDeposit());//押金
        yxtUser.setIdCardNo(user.getIdCardNo());//身份证
        yxtUser.setCheckIn(new Date());//入住日期
        yxtUser.setCheckOut(user.getCheckOut());//退房日期
        yxtUser.setUserRoomId(user.getUserRoomId());//房间号
        yxtUser.setUserStatus("0");//0为订单未结束 ；1为订单结束
        yxtUserMapper.insert(yxtUser);
    }

    /**
     * 分页查询所有客户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findUserListAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        YxtUserExample example = new YxtUserExample();
        example.setOrderByClause("check_in desc");
        Page<YxtUser> page = (Page<YxtUser>) yxtUserMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 查询正在入住的订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findUserList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        YxtUserExample example = new YxtUserExample();
        YxtUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserStatusEqualTo("0");//0为订单未完成；1为订单结束
        Page<YxtUser> page = (Page<YxtUser>) yxtUserMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 退房修改订单状态
     * @param userId
     */
    @Override
    public void updateStatusByUser(String userId) {
        YxtUser yxtUser = yxtUserMapper.selectByPrimaryKey(userId);
        yxtUser.setUserStatus("1");
        yxtUserMapper.updateByPrimaryKey(yxtUser);
    }
}
