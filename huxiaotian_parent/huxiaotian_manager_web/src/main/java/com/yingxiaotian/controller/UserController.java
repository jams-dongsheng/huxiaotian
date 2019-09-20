package com.yingxiaotian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huxiaotian.service.RoomService;
import com.huxiaotian.service.UserService;
import com.yingxiaotian.entity.PageResult;
import com.yingxiaotian.entity.RoomNum;
import com.yingxiaotian.group.Room;
import com.yingxiaotian.pojo.Result;
import com.yingxiaotian.pojo.YxtUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @Reference
    private RoomService roomService;

    /**
     * 根据房间号或客户姓名查询
     * @param search
     * @return
     */
    @RequestMapping("findByNameOrId")
    public Room findByNameOrId(String search){
        return userService.findByNameOrId(search);
    }

    /**
     * 客户入住
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody YxtUser user){
        try {
            //添加订单
            userService.add(user);
            //修改对应的房间状态
            roomService.updateStatusByIn(user.getUserRoomId());
            return new Result(true,"入住成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"入住失败");
        }
    }

    /**
     * 查询房间状态及其数量
     * @return
     */
    @RequestMapping("/findCount")
    public RoomNum findCount(){
        return roomService.findCount();
    }

    /**
     * 分页查询所有客户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findUserListAll")
    public PageResult findUserListAll(int pageNum, int pageSize){
       return userService.findUserListAll(pageNum,pageSize);
    }

    /**
     * 查询正在入住的客户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findUserList")
    public PageResult findUserList(int pageNum, int pageSize){
        return userService.findUserList(pageNum,pageSize);
    }

    /**
     * 查询所有空房间
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findEmptyRoom")
    public PageResult findEmptyRoom(int pageNum, int pageSize){
        return roomService.findEmptyRoom(pageNum,pageSize);
    }

    /**
     * 查询所有房间
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAllRoom")
    public PageResult findAllRoom(int pageNum, int pageSize){
        return roomService.findAllRoom(pageNum,pageSize);
    }

    /**
     * 退房
     * @param
     */
    @RequestMapping("/outRoom")
    public Result outRoom(String userId){
        try {
            //修改房间状态
            roomService.updateStatusByOut(userId);
            //修改订单状态
            userService.updateStatusByUser(userId);
            return new Result(true,"退房成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"退房失败");
        }
    }

    @RequestMapping("/findTime")
    public YxtUser findTime(String roomId){
        YxtUser user = userService.findTime(roomId);
        return user;
    }
}
