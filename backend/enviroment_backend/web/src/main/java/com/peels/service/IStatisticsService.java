package com.peels.service;

import com.peels.dto.*;
import com.peels.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.vo.PageResponseVo;

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

    List<AqiTrendTotalStatisticDto> listAqiTrendTotalStatis();

    List<AqiDistributeTotalStatisticDto> listAqiDistributeTotalStatis();

    List<ProvinceItemTotalStatisticDto> listProvinceItemTotalStatis();

    Statistics getStatisticsById(Integer id);

    PageResponseVo<Statistics> listStatisticsPage(StatisticsPageRequestDto statisticsPageRequestDto);

    int saveStatistics(Statistics statistics);
}
