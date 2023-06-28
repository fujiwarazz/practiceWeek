package com.peels.dto;

import lombok.Data;

@Data
public class ProvinceItemTotalStatisticDto {
    private Integer provinceId;
    private String provinceAbbr;
    private String provinceName;
    private Integer so2Total;
    private Integer coTotal;
    private Integer spmTotal;
    private Integer aqiTotal;
}
