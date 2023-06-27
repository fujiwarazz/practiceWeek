package com.peels.controller;


import com.peels.dto.*;
import com.peels.entity.Statistics;
import com.peels.service.IStatisticsService;
import com.peels.vo.PageResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {



    @Resource
    IStatisticsService statisticsService;

    @PostMapping("/saveStatistics")
    public int saveStatistics(@RequestBody Statistics statistics) {
        return statisticsService.saveStatistics(statistics);
    }

    @PostMapping("/listStatisticsPage")
    public PageResponseVo<Statistics> listStatisticsPage(@RequestBody StatisticsPageRequestDto statisticsPageRequestDto) {
        return statisticsService.listStatisticsPage(statisticsPageRequestDto);
    }

    @PostMapping("/getStatisticsById")
    public Statistics getStatisticsById(@RequestBody Statistics statistics) {
        return statisticsService.getStatisticsById(statistics.getId());
    }

    @PostMapping("/listProvinceItemTotalStatis")
    public List<ProvinceItemTotalStatisticDto> listProvinceItemTotalStatis() {
        return statisticsService.listProvinceItemTotalStatis();
    }

    @PostMapping("/listAqiDistributeTotalStatis")
    public List<AqiDistributeTotalStatisticDto> listAqiDistributeTotalStatis() {
        return statisticsService.listAqiDistributeTotalStatis();
    }

    @PostMapping("/listAqiTrendTotalStatis")
    public List<AqiTrendTotalStatisticDto> listAqiTrendTotalStatis() {
        return statisticsService.listAqiTrendTotalStatis();
    }

    @PostMapping("/getAqiCount")
    public long getAqiCount() {
        return statisticsService.getAqiCount();
    }

    @PostMapping("/getAqiGoodCount")
    public long getAqiGoodCount() {
        return statisticsService.getAqiGoodCount();
    }

    @PostMapping("/getProvinceCoverage")
    public String getProvinceCoverage() {
        return statisticsService.getProvinceCoverage();
    }

    @PostMapping("/getCityCoverage")
    public String getCityCoverage() {
        return statisticsService.getCityCoverage();
    }
}

