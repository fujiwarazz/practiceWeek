package com.peels.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseVo<T> {
    private Integer totalRow;       //总行数
    private Integer totalPageNum;   //总页数
    private Integer preNum;         //上一页
    private Integer nextNum;        //下一页
    private Integer pageNum;        //当前页
    private Integer maxPageNum;     //每页显示最大行数
    private Integer beginNum;       //开始查询记录数
    private List<T> list;           //业务数据
}
