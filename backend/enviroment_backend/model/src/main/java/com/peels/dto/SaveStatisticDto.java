package com.peels.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.peels.entity.Statistics;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 29/06/2023 23:06
 */
@Data
public class SaveStatisticDto {

    @ApiModelProperty("所属省区域编号")
    private Integer provinceId;

    @ApiModelProperty("所属市区域编号")
    private Integer cityId;

    @ApiModelProperty("反馈信息所在区域详细地址")
    private String address;

    @ApiModelProperty("空气二氧化硫浓度值（单位：μg/m3）")
    private Integer so2Value;

    @ApiModelProperty("空气二氧化硫指数级别")
    private Integer so2Level;

    @ApiModelProperty("空气一氧化碳浓度值（单位：μg/m3）")
    private Integer coValue;

    @ApiModelProperty("空气一氧化碳指数级别")
    private Integer coLevel;

    @ApiModelProperty("空气悬浮颗粒物浓度值（单位：μg/m3）")
    private Integer spmValue;

    @ApiModelProperty("空气PM2.5指数级别")
    private Integer spmLevel;

    @ApiModelProperty("空气质量指数级别")
    private Integer aqiId;

    @ApiModelProperty("空气质量指数值")
    private Integer afId;


    @ApiModelProperty("所属网格员编号")
    private Integer gmId;

    @ApiModelProperty("反馈者编号（公众监督员电话号码）")
    private String fdId;

    @ApiModelProperty("反馈信息描述")
    private String information;
}
