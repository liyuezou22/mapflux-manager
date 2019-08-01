package com.cq.szyk.mapfluxmodel.mapdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class Map implements Serializable {

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
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    private String stylejson;

    private String stylejsonurl;

    /**
     * 更新人id
     */
    private Long updateUserId;

    /**
     * 更新人姓名
     */
    private String updateUserName;

    /**
     * 地图名称
     */
    private String name;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 是否启用
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    /**
     * 标签
     */
    private String tag;

    /**
     * 权限类别（个人，公共）
     */
    private String authoritytype;

    /**
     * 缩略图url
     */
    private String thumbnailurl;


}
