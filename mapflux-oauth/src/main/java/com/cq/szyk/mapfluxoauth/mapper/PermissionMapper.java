package com.cq.szyk.mapfluxoauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cq.szyk.mapfluxmodel.users.Permission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author lyz
 * @since 2019-08-01
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findPerByUserId(Long id);

}
