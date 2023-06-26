package com.peels.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Getter
@Setter
@ApiModel(value = "Admins对象", description = "")
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("系统管理员编号")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    @ApiModelProperty("系统管理员登录编码")
    private String adminCode;

    @ApiModelProperty("系统管理员登录密码")
    private String password;

    @ApiModelProperty("备注")
    private String remarks;


}
