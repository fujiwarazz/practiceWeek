package com.peels.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 29/06/2023 22:25
 */
@Data
public class AdminLoginDto {
    @ApiModelProperty("系统管理员登录编码")
    @TableField("admin_code")
    private String adminCode;

    @ApiModelProperty("系统管理员登录密码")
    private String password;
}
