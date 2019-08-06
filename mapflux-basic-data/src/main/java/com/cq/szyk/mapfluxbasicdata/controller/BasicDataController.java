package com.cq.szyk.mapfluxbasicdata.controller;

import com.cq.szyk.mapfluxapi.data.BasicDataControllerApi;
import com.cq.szyk.mapfluxbasicdata.service.BasicDataService;
import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxmodel.basicdata.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author lyz
 * @describe 基本数据管理类
 * @date 2019/8/6
 */
@RestController
@RequestMapping("/data")
public class BasicDataController implements BasicDataControllerApi {
    @Autowired
    private BasicDataService basicDataService;

    @PostMapping("/fileUpload")
    @Override
    public Response fileUpload(MultipartFile file, Data data) {
        return basicDataService.fileUpload(file, data);
    }
}
