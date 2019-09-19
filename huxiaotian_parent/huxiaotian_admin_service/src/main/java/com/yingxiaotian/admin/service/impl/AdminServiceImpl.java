package com.yingxiaotian.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yingxiaotian.admin.service.AdminService;
import com.yingxiaotian.mapper.YxtAdminMapper;
import com.yingxiaotian.pojo.PageResult;
import com.yingxiaotian.pojo.YxtAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private YxtAdminMapper adminMapper;

    //查询所有非超级管理员
    @Override
    public List<YxtAdmin> findAll() {
        List<YxtAdmin> adminList = adminMapper.selectByExample(null);
        List<YxtAdmin> admins = new ArrayList<>();
        if (adminList!=null&&adminList.size()!=0){
            for (YxtAdmin admin : adminList) {
                if ("NORMAL".equals(admin.getRole())){
                    admins.add(admin);
                }
            }
        }
        return admins;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<YxtAdmin> page = (Page<YxtAdmin>) adminMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }


    //增加普通管理员
    @Override
    public void add(YxtAdmin yxtAdmin) {
        yxtAdmin.setRole("NORMAL");
        adminMapper.insert(yxtAdmin);
    }

    @Override
    public void update(YxtAdmin yxtAdmin) { }

    //根据用户名查找用于对象
    @Override
    public YxtAdmin findOne(String username) {
        return adminMapper.selectByPrimaryKey(username);
    }

    //删除普通用户
    @Override
    public void delete(String userId) {
        adminMapper.deleteByPrimaryKey(userId);
    }
}
