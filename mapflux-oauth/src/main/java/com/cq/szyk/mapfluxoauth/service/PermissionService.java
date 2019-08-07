package com.cq.szyk.mapfluxoauth.service;

import com.cq.szyk.mapfluxmodel.users.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findPerByUserId(Long id);
}
