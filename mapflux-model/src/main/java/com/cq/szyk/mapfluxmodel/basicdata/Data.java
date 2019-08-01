package com.cq.szyk.mapfluxmodel.basicdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据表
 * </p>
 *
 * @author lyz
 * @since 2019-08-01
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 跟新人ID
     */
    private Long updateUserId;

    /**
     * 更新人名称
     */
    private String updateUserName;

    /**
     * 数据名称
     */
    private String name;

    /**
     * 数据标签
     */
    private String tag;

    /**
     * 数据类型（geojson,shp,gdb,pg）
     */
    private String type;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 是否删除（0-未删除，1-删除）
     */
    private Integer delete;

    /**
     * 权限级别（个人，公共）
     */
    private String authoritytype;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件服务器名称
     */
    private String fileUuid;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 中心点
     */
    private String center;

    /**
     * 边框
     */
    private String bbox;

    private Long previewId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 跟新时间
     */
    private Date updateDate;


}
