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
@TableName("grid_city")
@ApiModel(value = "GridCity对象", description = "")
public class GridCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("系统网格覆盖市区域编号")
    @TableId(value = "city_id", type = IdType.AUTO)
    private Integer cityId;

    @ApiModelProperty("系统网格覆盖市区域名称")
    private String cityName;

    @ApiModelProperty("所属省区域编号")
    private Integer provinceId;

    @ApiModelProperty("备注")
    private String remarks;


}
