package com.peels.entity;

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
@ApiModel(value = "Supervisor对象", description = "")
public class Supervisor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公众监督员编号（即手机号码）")
    private String telId;

    @ApiModelProperty("公众监督员登录密码")
    private String password;

    @ApiModelProperty("公众监督员真实姓名")
    private String realName;

    @ApiModelProperty("公众监督员出生日期")
    private String birthday;

    @ApiModelProperty("公众监督员性别（1：男；0：女）")
    private Integer sex;

    @ApiModelProperty("备注")
    private String remarks;


}
