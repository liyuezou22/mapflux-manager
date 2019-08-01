package com.cq.szyk.mapfluxmodel.serverdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建用户id
     */
    private Long createUserId;

    /**
     * 更新用户id
     */
    private Long updateUserId;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签
     */
    private String tag;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 删除（0-未删除，1-已删除）
     */
    private Integer delete;

    /**
     * 权限类别（个人，公共）
     */
    private String authoritytype;

    /**
     * 范围
     */
    private String bbox;

    /**
     * 中心点
     */
    private String center;

    /**
     * 最大值
     */
    private Integer maxZoom;

    /**
     * 最小值
     */
    private Integer minZoom;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;


}
