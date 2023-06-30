package com.peels.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.peels.entity.AqiFeedback;
import com.peels.entity.GridCity;
import com.peels.entity.GridProvince;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 30/06/2023 08:12
 */
@Data
public class FeedBackVo {

    @ApiModelProperty("空气质量公众监督反馈信息编号")
    private Integer afId;

    @ApiModelProperty("系统网格覆盖市区域名称")
    private String cityName;

    @ApiModelProperty("系统网格覆盖省区域名称")
    private String provinceName;

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
}
