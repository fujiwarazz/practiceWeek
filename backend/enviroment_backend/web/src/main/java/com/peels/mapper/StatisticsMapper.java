package com.peels.mapper;

import com.peels.dto.AqiDistributeTotalStatisticDto;
import com.peels.dto.AqiTrendTotalStatisticDto;
import com.peels.dto.ProvinceItemTotalStatisticDto;
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

    public List<AqiTrendTotalStatisticDto> listAqiTrendTotalStatis(List<AqiTrendTotalStatisticDto> list);

    List<AqiDistributeTotalStatisticDto> listAqiDistributeTotalStatis();

    List<ProvinceItemTotalStatisticDto> listProvinceItemTotalStatis();

    Statistics getStatisticsById(Integer id);

//    public int getStatisticsCount(StatisticsPageRequestDto statisticsPageRequestDto);
//
//    List<Statistics> listStatisticsPage(StatisticsPageRequestDto statisticsPageRequestDto);
}
