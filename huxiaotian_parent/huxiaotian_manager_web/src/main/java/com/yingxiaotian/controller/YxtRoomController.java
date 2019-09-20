package com.yingxiaotian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huxiaotian.service.RoomService;
import com.yingxiaotian.pojo.YxtRoom;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class YxtRoomController {

    @Reference(timeout = 5000)
    private RoomService roomService;

    /**
     * 添加房间
     * @param yxtRoom
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody YxtRoom yxtRoom){
        String roomId = yxtRoom.getRoomId();
        if (roomService.findOne(roomId)==null){
            try {
                roomService.add(yxtRoom);
                return new Result(true,"增加成功");
            }catch (Exception e){
                return new Result(false,"增加失败");
            }
        }else {
            return new Result(false,"该房间已存在");
        }
    }

    /**
     * 删除房间
     * @param roomId
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(String roomId){
        try {
            roomService.delete(roomId);
            return new Result(true,"删除成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/findOne")
    public YxtRoom findOne(String roomId){
        return roomService.findOne(roomId);
    }



    @RequestMapping("/update")
    public Result update(@RequestBody YxtRoom yxtRoom){
        YxtRoom yxtRoom1 = roomService.findOne(yxtRoom.getRoomId());

        if (!yxtRoom1.equals(yxtRoom.getRoomId())){
            return new Result(false,"非法操作");
        }
        try {
            roomService.update(yxtRoom);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
}
