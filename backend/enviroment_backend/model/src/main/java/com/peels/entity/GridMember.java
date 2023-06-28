package com.peels.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("grid_member")
@ApiModel(value = "GridMember对象", description = "")
public class  GridMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("网格员编号")
      @TableId(value = "gm_id", type = IdType.AUTO)
    private Integer gmId;

    @ApiModelProperty("网格员名称")
    private String gmName;

    @ApiModelProperty("网格员登录编码")
    private String gmCode;

    @ApiModelProperty("格网员登录密码")
    private String password;

    @ApiModelProperty("网格区域：省编号")
    private Integer provinceId;

    @ApiModelProperty("网格区域：市编号")
    private Integer cityId;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("网格员状态（0:工作状态; 1:非工作状态（由考勤系统管理）; 2:其它）")
    private Integer state;

    @ApiModelProperty("备注")
    private String remarks;


}
