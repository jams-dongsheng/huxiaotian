package com.huxiaotian.service;


import com.yingxiaotian.entity.PageResult;
import com.yingxiaotian.group.Room;
import com.yingxiaotian.pojo.YxtUser;

public interface UserService {

    /**
     * 通过住户姓名或者房间号查询房间
     * @return
     */
    public Room findByNameOrId(String search);

    /**
     * 入住
     * @param user
     * @return
     */
    public void add(YxtUser user);

    /**
     * 分页查询所有客户列表
     * @return
     */
    public PageResult findUserListAll(int pageNum, int pageSize);

    /**
     * 查询正在入住的客户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findUserList(int pageNum, int pageSize);

    /**
     * 退房修改订单状态
     * @param roomId
     */
    public void updateStatusByUser(String roomId);
}
