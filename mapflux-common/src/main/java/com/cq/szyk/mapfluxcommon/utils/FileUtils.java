package com.cq.szyk.mapfluxcommon.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUtils {
    //nginx中，gofast集群负载均衡路径
    private static String UPLOAD_URL;

    static {
        UPLOAD_URL = "http://192.168.139.133/upload";
    }

    private FileUtils() {

    }

    /**
     * 将 MultipartFile 转换为 File
     */
    public static File changeToFile(MultipartFile file, String fileType) {
        try {
            if ("geojson".equals(fileType)) {
                File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".geojson");
                file.transferTo(tempFile);
                return tempFile;
            }
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".zip");
            file.transferTo(tempFile);
            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传至分布式资源服务器中 go-fast
     * PS:此处文件是经过转换的，故而名称文件名称已经更改为UUID了，所以不会出现重复的情况
     * PS:userName->用户名
     */
    public static JSONObject fileUpload_Geojson(File file, String userName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", file);
        map.put("output", "json");
        map.put("path", "mapflux_text/" + userName);
        map.put("scene", "mapflux_text/" + userName);
        String post = HttpUtil.post(UPLOAD_URL, map);
        return JSONObject.parseObject(post);
    }

    /**
     * 提取公钥
     * */
    public static String getPublicKey(Resource resource){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            return null;
        }
    }

}
