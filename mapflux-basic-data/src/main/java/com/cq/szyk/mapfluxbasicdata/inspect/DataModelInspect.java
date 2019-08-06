package com.cq.szyk.mapfluxbasicdata.inspect;

import com.cq.szyk.mapfluxcommon.expetion.ExceptionCast;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxmodel.basicdata.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lyz
 * @describe 用于处理非空判断
 * @date 2019/8/6
 */
public class DataModelInspect {

    private DataModelInspect() {

    }

    public static void fileUploadInspect(MultipartFile file, Data data) {
        if (file.isEmpty()) {
            ExceptionCast.cast(CommonCode.FILE_NULL);
        }
        if (data.getName() == null || "".equals(data.getName())) {
            ExceptionCast.cast(CommonCode.NAME_NULL);
        }
        if (data.getType() == null || "".equals(data.getType())) {
            ExceptionCast.cast(CommonCode.TYPE_NULL);
        }
        if(data.getTag() == null || "".equals(data.getTag())){
            ExceptionCast.cast(CommonCode.TAG_NULL);
        }
    }

}
