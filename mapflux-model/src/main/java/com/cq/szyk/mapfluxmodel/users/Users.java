package com.cq.szyk.mapfluxmodel.users;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
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
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 企业名称
     */
    private String companyname;

    /**
     * 企业性质
     */
    private String companynature;

    /**
     * 企业地址
     */
    private String companyaddress;

    /**
     * 所在部门
     */
    private String departmentname;

    /**
     * 职位
     */
    private String position;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 座机号
     */
    private String phone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;


}
