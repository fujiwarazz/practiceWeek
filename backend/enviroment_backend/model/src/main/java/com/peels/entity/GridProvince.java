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
@TableName("grid_province")
@ApiModel(value = "GridProvince对象", description = "")
public class GridProvince implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("系统网格覆盖省区域编号")
      @TableId(value = "province_id", type = IdType.AUTO)
    private Integer provinceId;

    @ApiModelProperty("系统网格覆盖省区域名称")
    private String provinceName;

    @ApiModelProperty("系统网格覆盖省区域简称")
    private String provinceAbbr;

    @ApiModelProperty("备注")
    private String remarks;


}
