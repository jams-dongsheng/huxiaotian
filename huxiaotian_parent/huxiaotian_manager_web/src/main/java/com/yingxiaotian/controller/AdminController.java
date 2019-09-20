package com.yingxiaotian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yingxiaotian.admin.service.AdminService;
import com.yingxiaotian.pojo.PageResult;
import com.yingxiaotian.pojo.Result;
import com.yingxiaotian.pojo.YxtAdmin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Reference
    private AdminService adminService;

    //获取登录的用户名
    @RequestMapping("/getUsername")
    public String getUsername(){
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    //检查登录输入框
    @RequestMapping("/loginCheck")
    public Result loginCheck(@RequestBody YxtAdmin admin){
        //根据用户名在数据库中进行查询
        YxtAdmin yxtAdmin = adminService.findOne(admin.getUsername());
        if (yxtAdmin==null){
            //1用户名不存在
            return new Result(false,"用户名不存在");
        }else {
            //2用户名存在
            //2.1判断密码
            if (yxtAdmin.getPassword().equals(admin.getPassword())){
                return new Result(true,"ok");
            }else {
                return new Result(false,"密码错误");
            }
        }

    }
    //增加普通管理员
    @RequestMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    public Result add(@RequestBody YxtAdmin admin){
        try {
            adminService.add(admin);
            return new Result(true,"增加角色成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"增加角色失败");
        }
    }

    //删除普通管理员
    @RequestMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    public Result delete(String userID){
        try {
            adminService.delete(userID);
            return new Result(true,"删除角色成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"删除角色失败");
        }
    }

    //查询所有普通管理员
    @RequestMapping("/findAllNormal")
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    public List<YxtAdmin> findAllNormal(){
        try {
             return adminService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //分页查询
    @RequestMapping("findPage")
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    public PageResult findPage(int page, int rows){
        return adminService.findPage(page, rows);
    }
}
