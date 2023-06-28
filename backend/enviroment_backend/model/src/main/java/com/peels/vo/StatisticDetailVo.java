package com.peels.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.peels.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 28/06/2023 14:49
 */
@Data
public class StatisticDetailVo  {

    @ApiModelProperty("统计信息编号")
    private Integer id;

    @ApiModelProperty("所属省区域编号")
    private Integer provinceId;

    @ApiModelProperty("系统网格覆盖省区域名称")
    private String provinceName;

    @ApiModelProperty("所属市区域编号")
    private Integer cityId;

    @ApiModelProperty("系统网格覆盖市区域名称")
    private String cityName;

    @ApiModelProperty("反馈信息所在区域详细地址")
    private String address;

    @ApiModelProperty("空气质量指数级别")
    private Integer aqiId;

    @ApiModelProperty("空气质量指数级别汉字表述")
    private String chineseExplain;

    @ApiModelProperty("空气质量指数级别描述")
    private String aqiExplain;

    @ApiModelProperty("空气质量指数级别表示颜色")
    private String color;

    @ApiModelProperty("对健康影响情况")
    private String healthImpact;

    @ApiModelProperty("确认日期")
    private String confirmDate;

    @ApiModelProperty("确认时间")
    private String confirmTime;

    @ApiModelProperty("所属网格员编号")
    private Integer gmId;

    @ApiModelProperty("网格员名称")
    private String gmName;

    @ApiModelProperty("反馈者编号（公众监督员电话号码）")
    private String fdId;

    @ApiModelProperty("公众监督员真实姓名")
    private String realName;

    @ApiModelProperty("反馈信息描述")
    private String information;
}
