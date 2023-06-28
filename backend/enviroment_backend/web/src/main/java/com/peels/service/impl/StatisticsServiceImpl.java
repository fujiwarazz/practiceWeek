package com.peels.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peels.dto.*;
import com.peels.entity.AqiFeedback;
import com.peels.entity.Statistics;
import com.peels.mapper.GridCityMapper;
import com.peels.mapper.GridProvinceMapper;
import com.peels.mapper.StatisticsMapper;
import com.peels.service.IStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.vo.AqiDetailVo;
import com.peels.vo.PageResponseVo;
import com.peels.vo.StatisticDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements IStatisticsService {


    @Resource
    private GridCityMapper gridCityMapper;

    @Resource
    private GridProvinceMapper gridProvinceMapper;

    @Resource
    private StatisticsMapper statisticsMapper;



    @Override
    public List<AqiDistributeTotalStatisticDto> listAqiDistributeTotalStatis() {
        return statisticsMapper.listAqiDistributeTotalStatis();
    }

    @Override
    public List<ProvinceItemTotalStatisticDto> listProvinceItemTotalStatis() {
        return statisticsMapper.listProvinceItemTotalStatis();
    }

    @Override
    public Statistics getStatisticsById(Integer id) {
        return statisticsMapper.getStatisticsById(id);
    }

    @Override
    @SuppressWarnings("all")
    public PageResponseVo<StatisticDetailVo> listStatisticsPage(StatisticsPageRequestDto request) {

        request.setPageNum((request.getPageNum() <= 1 ? 0 : request.getPageNum() - 1) * request.getMaxPageNum());


        List<StatisticDetailVo>list = statisticsMapper.listStatisticsPage(request);
        Integer count = statisticsMapper.getStatisicCount(request);

        PageResponseVo<StatisticDetailVo> statisticDetailVoPageResponseVo = new PageResponseVo<>();
        statisticDetailVoPageResponseVo.setList(list);
        statisticDetailVoPageResponseVo.setPageNum(list.size() % request.getMaxPageNum());
        statisticDetailVoPageResponseVo.setBeginNum(request.getBeginNum());
        statisticDetailVoPageResponseVo.setTotalRow(count);
        statisticDetailVoPageResponseVo.setNextNum((request.getPageNum() * request.getMaxPageNum()>=count?request.getPageNum()+1:request.getPageNum()));
        statisticDetailVoPageResponseVo.setPreNum(request.getPageNum()<=1 ? 1:request.getPageNum()-1);
        statisticDetailVoPageResponseVo.setTotalPageNum(count % request.getMaxPageNum() == 0 ? count / request.getMaxPageNum() : count / request.getMaxPageNum() + 1);


        statisticDetailVoPageResponseVo.setMaxPageNum(request.getMaxPageNum());

        return statisticDetailVoPageResponseVo;

    }

    @Override
    public int saveStatistics(Statistics statistics) {
        if (statistics.getId() == null ) {
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }
        this.saveOrUpdate(statistics);
        return 200;
    }

    @Override
    public List<AqiTrendTotalStatisticDto> listAqiTrendTotalStatis() {
        //获取当前12个月列表，作为查询参数
        List<AqiTrendTotalStatisticDto> parameList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        for(int i=0;i<12;i++) {
            //计算当前日期的前一个月
            calendar.add(Calendar.MONTH, -1);
            AqiTrendTotalStatisticDto dto = new AqiTrendTotalStatisticDto();
            dto.setMonth(sdf.format(calendar.getTime()));
            parameList.add(dto);
        }
        return statisticsMapper.listAqiTrendTotalStatis(parameList);
    }



    @Override
    public long getAqiCount() {
        return statisticsMapper.selectCount(null);
    }
    @Override
    public long getAqiGoodCount() {
        QueryWrapper<Statistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("aqi_id", 2);
        return statisticsMapper.selectCount(queryWrapper.le("aqi_id", 2));
    }

    @Override
    public String getProvinceCoverage() {
        long provinceNum = gridProvinceMapper.selectCount(null);
        return String.format("%.2f",(provinceNum/(double)34)*100);
    }

    @Override
    public String getCityCoverage() {
        long cityNum = gridCityMapper.selectCount(null);
        return String.format("%.2f",(cityNum/(double)106)*100);
    }


}
