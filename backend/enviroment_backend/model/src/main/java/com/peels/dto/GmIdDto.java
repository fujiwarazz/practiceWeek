package com.peels.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author peelsannaw
 * @create 29/06/2023 22:35
 */
@Data
public class GmIdDto {
    @ApiModelProperty("网格员编号")
    @TableId(value = "gm_id", type = IdType.AUTO)
    private Integer gmId;

}
