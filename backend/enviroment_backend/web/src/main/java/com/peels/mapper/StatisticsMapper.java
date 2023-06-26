package com.peels.mapper;

import com.peels.dto.AqiDistributeTotalStatisDto;
import com.peels.dto.AqiTrendTotalStatisDto;
import com.peels.dto.ProvinceItemTotalStatisDto;
import com.peels.dto.StatisticsPageRequestDto;
import com.peels.entity.Statistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Mapper
public interface StatisticsMapper extends BaseMapper<Statistics> {

    public List<AqiTrendTotalStatisDto> listAqiTrendTotalStatis(List<AqiTrendTotalStatisDto> list);

    List<AqiDistributeTotalStatisDto> listAqiDistributeTotalStatis();

    List<ProvinceItemTotalStatisDto> listProvinceItemTotalStatis();

    Statistics getStatisticsById(Integer id);

//    public int getStatisticsCount(StatisticsPageRequestDto statisticsPageRequestDto);
//
//    List<Statistics> listStatisticsPage(StatisticsPageRequestDto statisticsPageRequestDto);
}
