package com.peels.dto;

import lombok.Data;

/**
 * @Author peelsannaw
 * @create 27/06/2023 09:54
 */
@Data
public class PageRequestDto {

    //分页请求参数
    private Integer pageNum;        //当前页
    private Integer maxPageNum;     //每页显示最大行数
    private Integer beginNum;       //开始查询记录数
}
