package com.cq.szyk.mapfluxbasicdata.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cq.szyk.mapfluxbasicdata.client.UsersClient;
import com.cq.szyk.mapfluxbasicdata.inspect.DataModelInspect;
import com.cq.szyk.mapfluxbasicdata.mapper.DataMapper;
import com.cq.szyk.mapfluxcommon.expetion.ExceptionCast;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxcommon.response.ResponseResult;
import com.cq.szyk.mapfluxcommon.utils.FileUtils;
import com.cq.szyk.mapfluxmodel.basicdata.Data;
import com.cq.szyk.mapfluxmodel.users.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

@Service
public class BasicDataService {
    private static final Logger LOG = LoggerFactory.getLogger(BasicDataService.class);
    @Resource
    private UsersClient usersClient;
    @Resource
    private DataMapper dataMapper;

    /**
     * 文件上传
     */
    public Response fileUpload(MultipartFile multipartFile, Data data) {
        //远程调用，获取当前登陆用户的信息,如果为空，则代表未登陆
        Users userInfo = usersClient.getUserInfo();
        if (userInfo == null) {
            ExceptionCast.cast(CommonCode.NOT_LOGIN);
        }
        //非空校验
        DataModelInspect.fileUploadInspect(multipartFile, data);
        //在文件转换前获取文件名称
        String fileName_old = multipartFile.getOriginalFilename();

        //文件转换
        File file = FileUtils.changeToFile(multipartFile, data.getType());
        //获取文件名称
        String fileName_new = file.getName();
        //文件上传后返回详细信息
        String saveFileInfo = "";
        //根据不同的类型进行封装不同的操作
        if ("geojson".equals(data.getType().toLowerCase())) {
            //上传geojson文件
            saveFileInfo = uploadGeojsonFile(file);
        } else if ("pg".equals(data.getType().toLowerCase())) {
            //存储pg数据文件
        } else {
            //shp,gdb 文件上传需要解压

        }
        //获取文件路径
        JSONObject saveFileJson = JSONObject.parseObject(saveFileInfo);
        String path = saveFileJson.get("path").toString();
        //如果文件上传失败，则会抛出异常，如果上传成功，执行以下方法
        Data newData = Data.builder().createUserName(userInfo.getUsername())
                .createUserId(userInfo.getId())
                .updateUserName(userInfo.getUsername())
                .updateUserId(userInfo.getId())
                .name(data.getName())
                .tag(data.getTag())
                .tag(data.getType())
                .thumbnail(null)
                .delete(0)
                .authoritytype("个人")
                .fileName(fileName_old)
                .fileUuid(fileName_new)
                .size(multipartFile.getSize()+"")
                .path(path)
                .createDate(new Date())
                .updateDate(new Date()).build();
        int insert = dataMapper.insert(newData);
        if(insert<=0){
            ExceptionCast.cast(CommonCode.FILE_UPLOAD_FAILS);
        }
        return new ResponseResult(CommonCode.SUCCESS, newData);
    }

    //geojson文件上传
    private String uploadGeojsonFile(File file) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", file);
        map.put("output", "json");
        map.put("path", "mapflux_text");
        map.put("scene", "mapflux_text");
        try {
            String post = HttpUtil.post("http://192.168.139.133/upload", map);
            LOG.info("文件上传成功：" + post);
            return post;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            ExceptionCast.cast(CommonCode.FILE_UPLOAD_FAILS);
        }
        return null;
    }


}
