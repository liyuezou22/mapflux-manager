package com.cq.szyk.mapfluxoauth.service.impl;

import com.cq.szyk.mapfluxmodel.users.Permission;
import com.cq.szyk.mapfluxoauth.mapper.PermissionMapper;
import com.cq.szyk.mapfluxoauth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findPerByUserId(Long id) {

        return permissionMapper.findPerByUserId(id);
    }
}
