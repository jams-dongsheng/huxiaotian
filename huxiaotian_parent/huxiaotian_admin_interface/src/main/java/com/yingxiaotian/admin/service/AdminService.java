package com.yingxiaotian.admin.service;

import com.yingxiaotian.pojo.YxtAdmin;

import java.util.List;

public interface AdminService {
    /**
     * 返回全部列表
     * @return
     */
    public List<YxtAdmin> findAll();



    /**
     * 增加
     */
    public void add(YxtAdmin user);


    /**
     * 修改
     */
    public void update(YxtAdmin user);


    /**
     * 根据ID获取实体
     * @return
     */
    public YxtAdmin findOne(String username);


    /**
     * 批量删除
     * @param ids
     */
    public void delete(Long[] ids);
}
