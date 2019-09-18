package com.yingxiaotian.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Service;


import com.yingxiaotian.admin.service.AdminService;
import com.yingxiaotian.mapper.YxtAdminMapper;
import com.yingxiaotian.pojo.YxtAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private YxtAdminMapper adminMapper;

    @Override
    public List<YxtAdmin> findAll() {
        return null;
    }

    @Override
    public void add(YxtAdmin yxtAdmin) {

    }

    @Override
    public void update(YxtAdmin yxtAdmin) {

    }

    @Override
    public YxtAdmin findOne(String username) {
        return adminMapper.selectByPrimaryKey(username);
    }

    @Override
    public void delete(Long[] longs) {

    }
}
