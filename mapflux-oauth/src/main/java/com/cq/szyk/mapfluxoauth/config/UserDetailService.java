package com.cq.szyk.mapfluxoauth.config;

import com.cq.szyk.mapfluxcommon.expetion.ExceptionCast;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxmodel.users.Permission;
import com.cq.szyk.mapfluxmodel.users.Users;
import com.cq.szyk.mapfluxoauth.service.PermissionService;
import com.cq.szyk.mapfluxoauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取用户
        Users users = userService.findUserByUserName(userName);
        if (users != null) {
            //设置用户权限
            ArrayList<GrantedAuthority> list = new ArrayList<>();
            //查询权限
            List<Permission> permissions = permissionService.findPerByUserId(users.getId());
            permissions.forEach(permission -> {
                if(permission == null){
                    return;
                }
                list.add(new SimpleGrantedAuthority(permission.getPercode()));
            });
            return new User(userName, users.getPassword(), list);
        } else {
            throw new UsernameNotFoundException("username not found!");
        }
    }
}
