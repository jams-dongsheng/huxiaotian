package com.yingxiaotian.controller;

import com.yingxiaotian.admin.service.AdminService;
import com.yingxiaotian.pojo.Result;
import com.yingxiaotian.pojo.YxtAdmin;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Reference
    private AdminService adminService;

    //获取登录的用户名
    @RequestMapping("getUsername")
    public String getUsername(){
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    //获取登录的用户名
    @RequestMapping("getUsername")
    public Result loginCheck(YxtAdmin admin){
        //1.根据用户名在数据库中进行查询
        adminService.findOne(admin.getUsername());
        return null;
    }
}
