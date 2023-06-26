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
@TableName("aqi_feedback")
@ApiModel(value = "AqiFeedback对象", description = "")
public class AqiFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("空气质量公众监督反馈信息编号")
    @TableId(value = "af_id", type = IdType.AUTO)
    private Integer afId;

    @ApiModelProperty("所属公众监督员编号（即手机号码）")
    private String telId;

    @ApiModelProperty("反馈信息所在省区域编号")
    private Integer provinceId;

    @ApiModelProperty("反馈信息所在市区域编号")
    private Integer cityId;

    @ApiModelProperty("反馈信息所在区域详细地址")
    private String address;

    @ApiModelProperty("反馈信息描述")
    private String information;

    @ApiModelProperty("反馈者对空气质量指数级别的预估等级")
    private Integer estimatedGrade;

    @ApiModelProperty("反馈日期")
    private String afDate;

    @ApiModelProperty("反馈时间")
    private String afTime;

    @ApiModelProperty("指派网格员编号")
    private Integer gmId;

    @ApiModelProperty("指派日期")
    private String assignDate;

    @ApiModelProperty("指派时间")
    private String assignTime;

    @ApiModelProperty("信息状态: 0:未指派; 1:已指派; 2:已确认")
    private Integer state;

    @ApiModelProperty("备注")
    private String remarks;


}
