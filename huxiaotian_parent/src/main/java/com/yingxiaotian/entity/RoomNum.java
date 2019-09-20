package com.yingxiaotian.entity;

import java.io.Serializable;

public class RoomNum implements Serializable {

    private Long totalRoom;

    private Long emptyRoom;

    private Long fullRoom;

    public Long getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(Long totalRoom) {
        this.totalRoom = totalRoom;
    }

    public Long getEmptyRoom() {
        return emptyRoom;
    }

    public void setEmptyRoom(Long emptyRoom) {
        this.emptyRoom = emptyRoom;
    }

    public Long getFullRoom() {
        return fullRoom;
    }

    public void setFullRoom(Long fullRoom) {
        this.fullRoom = fullRoom;
    }
}
