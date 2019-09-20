package com.yingxiaotian.group;


import com.yingxiaotian.pojo.YxtRoom;
import com.yingxiaotian.pojo.YxtUser;

import java.io.Serializable;

public class Room implements Serializable {

    private YxtRoom YxtRoom;

    private YxtUser YxtUser;

    public YxtRoom getYxtRoom() {
        return YxtRoom;
    }

    public void setYxtRoom(YxtRoom YxtRoom) {
        this.YxtRoom = YxtRoom;
    }

    public YxtUser getYxtUser() {
        return YxtUser;
    }

    public void setYxtUser(YxtUser YxtUser) {
        this.YxtUser = YxtUser;
    }
}
