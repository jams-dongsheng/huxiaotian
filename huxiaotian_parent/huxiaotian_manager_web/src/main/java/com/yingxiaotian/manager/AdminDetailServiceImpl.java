package com.yingxiaotian.manager;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.yingxiaotian.admin.service.AdminService;
import com.yingxiaotian.pojo.YxtAdmin;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AdminDetailServiceImpl implements UserDetailsService {

    @Reference(timeout = 8000)
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        //1.根据登录用户名查找用户
        YxtAdmin admin = adminService.findOne(username);
        List<SimpleGrantedAuthority> authorties = new ArrayList<>();
        authorties.add(new SimpleGrantedAuthority("ROLE_"+admin.getRole()));
        System.out.println(admin.getUsername()+admin.getPassword()+admin.getRole());
        return new User(username,admin.getPassword(),authorties);
    }
}
