package com.cq.szyk.mapfluxapi.data;

import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxmodel.basicdata.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;


@Api(value = "基本数据管理类", description = "提供基本数据（shp,gdb,geojson）等数据管理接口")
public interface BasicDataControllerApi {
    @ApiOperation(value = "文件上传")
    Response fileUpload(MultipartFile file, Data data);

}
