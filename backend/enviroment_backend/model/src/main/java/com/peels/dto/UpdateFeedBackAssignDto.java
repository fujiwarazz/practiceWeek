package com.peels.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 29/06/2023 22:14
 */
@Data
public class UpdateFeedBackAssignDto {

    private Integer afId;

    @ApiModelProperty("指派网格员编号")
    private Integer gmId;

    @ApiModelProperty("信息状态: 0:未指派; 1:已指派; 2:已确认")
    private Integer state;
}
