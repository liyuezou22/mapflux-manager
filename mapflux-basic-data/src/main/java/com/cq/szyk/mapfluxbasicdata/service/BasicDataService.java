package com.cq.szyk.mapfluxbasicdata.service;

import com.cq.szyk.mapfluxbasicdata.inspect.DataModelInspect;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxcommon.response.ResponseResult;
import com.cq.szyk.mapfluxcommon.utils.FileUtils;
import com.cq.szyk.mapfluxmodel.basicdata.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BasicDataService {
    /**
     * 文件上传
     */
    public Response fileUpload(MultipartFile multipartFile, Data data) {
        //非空校验
        DataModelInspect.fileUploadInspect(multipartFile, data);
        //文件转换
        File file = FileUtils.changeToFile(multipartFile, data.getType());
        //获取文件名称
        String name = file.getName();
        //根据不同的类型进行封装不同的操作
        if ("geojson".equals(data.getType().toLowerCase())) {
            //上传geojson文件

        } else if ("pg".equals(data.getType().toLowerCase())) {
            //存储pg数据文件
        } else {
            //shp,gdb 文件上传需要解压

        }
        return new ResponseResult(CommonCode.FAILS, null);
    }

    //geojson文件上传
    private void uploadGeojsonFile(MultipartFile file){

    }



}
