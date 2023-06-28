package com.peels.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.peels.entity.AqiFeedback;
import com.peels.entity.GridMember;
import com.peels.entity.Supervisor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 28/06/2023 10:59
 */
@Data
public class AqiDetailVo extends AqiFeedback {

    @ApiModelProperty("网格员编号")
    private Integer gmId;

    @ApiModelProperty("系统网格覆盖市区域名称")
    private String cityName;

    @ApiModelProperty("网格联系电话")
    private String tel;

    @ApiModelProperty("网格员名称")
    private String gmName;

    @ApiModelProperty("公众监督员编号（即手机号码）")
    private String telId;

    @ApiModelProperty("公众监督员真实姓名")
    private String realName;

    @ApiModelProperty("系统网格覆盖省区域名称")
    private String provinceName;

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
}
