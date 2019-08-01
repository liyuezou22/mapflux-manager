package com.cq.szyk.mapfluxmodel.basicdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyz
 * @since 2019-08-01
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataPreview implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 更新人id
     */
    private Long updateUserId;

    /**
     * 更新人名称
     */
    private String updateUserName;

    /**
     * 获取tilejson的链接
     */
    private String url;

    /**
     * 中心点
     */
    private String center;

    /**
     * 边框
     */
    private String bbox;

    /**
     * 最小缩放比例
     */
    private Integer minZoom;

    /**
     * 最大缩放比例
     */
    private Integer maxZoom;

    /**
     * tilejson
     */
    private String tilejson;


}
