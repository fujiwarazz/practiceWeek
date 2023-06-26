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
@ApiModel(value = "Aqi对象", description = "")
public class Aqi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("空气质量指数级别(共六级)")
    @TableId(value = "aqi_id", type = IdType.AUTO)
    private Integer aqiId;

    @ApiModelProperty("空气质量指数级别汉字表述")
    private String chineseExplain;

    @ApiModelProperty("空气质量指数级别描述")
    private String aqiExplain;

    @ApiModelProperty("空气质量指数级别表示颜色")
    private String color;

    @ApiModelProperty("对健康影响情况")
    private String healthImpact;

    @ApiModelProperty("建议采取的措施")
    private String takeSteps;

    @ApiModelProperty("本级别二氧化硫浓度最小限值")
    private Integer so2Min;

    @ApiModelProperty("本级别二氧化硫浓度最大限值")
    private Integer so2Max;

    @ApiModelProperty("本级别一氧化碳浓度最小限值")
    private Integer coMin;

    @ApiModelProperty("本级别一氧化碳浓度最大限值")
    private Integer coMax;

    @ApiModelProperty("本级别悬浮颗粒物浓度最小限值")
    private Integer spmMin;

    @ApiModelProperty("本级别悬浮颗粒物浓度最大限值")
    private Integer spmMax;

    @ApiModelProperty("备注")
    private String remarks;


}
