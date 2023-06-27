package com.peels.dto;

import lombok.Data;

@Data
public class StatisticsPageRequestDto extends PageRequestDto{

    private Integer provinceId;
    private Integer cityId;
    private String confirmDate;

}
