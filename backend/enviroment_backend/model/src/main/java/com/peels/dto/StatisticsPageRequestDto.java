package com.peels.dto;

import lombok.Data;

@Data
public class StatisticsPageRequestDto {
    private Integer provinceId;
    private Integer cityId;
    private String confirmDate;

    // 分页请求参数
    private Integer pageNum; // 当前页
    private Integer maxPageNum; // 每页显示最大行数
    private Integer beginNum; // 开始查询记录数
}
