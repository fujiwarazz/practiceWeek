package com.peels.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 26/06/2023 16:09
 */
@Data
public class AdminsVo {
    @ApiModelProperty("系统管理员编号")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    @ApiModelProperty("系统管理员登录编码")
    @TableField("admin_code")
    private String adminCode;


    @ApiModelProperty("备注")
    private String remarks;
}
