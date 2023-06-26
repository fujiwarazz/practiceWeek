package com.peels.service;

import com.peels.dto.*;
import com.peels.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.utils.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IStatisticsService extends IService<Statistics> {

    long getAqiCount();

    long getAqiGoodCount();
    String getProvinceCoverage();
    String getCityCoverage();

    List<AqiTrendTotalStatisDto> listAqiTrendTotalStatis();

    List<AqiDistributeTotalStatisDto> listAqiDistributeTotalStatis();

    List<ProvinceItemTotalStatisDto> listProvinceItemTotalStatis();

    Statistics getStatisticsById(Integer id);

    PageResponseDto<Statistics> listStatisticsPage(StatisticsPageRequestDto statisticsPageRequestDto);

    int saveStatistics(Statistics statistics);
}
