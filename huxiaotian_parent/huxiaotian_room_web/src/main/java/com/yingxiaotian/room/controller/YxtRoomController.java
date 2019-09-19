package com.yingxiaotian.room.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yingxiaotian.pojo.YxtRoom;
import com.yingxiaotian.room.service.YxtRoomService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class YxtRoomController {

    @Reference(timeout = 5000)
    private YxtRoomService yxtRoomService;

    /**
     * 添加房间
     * @param yxtRoom
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody YxtRoom yxtRoom){
        String roomId = yxtRoom.getRoomId();
        if (yxtRoomService.findOne(roomId)==null){
            try {
                yxtRoomService.add(yxtRoom);
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
            yxtRoomService.delete(roomId);
            return new Result(true,"删除成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/findOne")
    public YxtRoom findOne(String roomId){
        return yxtRoomService.findOne(roomId);
    }
}
