package com.cq.szyk.mapfluxbasicdata.client;

import com.cq.szyk.mapfluxcommon.filter.FeignFilter;
import com.cq.szyk.mapfluxmodel.users.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "mapflux-user",configuration = FeignFilter.class)
public interface UsersClient {
    @GetMapping("/user/getUserInfo")
    Users getUserInfo();
}
