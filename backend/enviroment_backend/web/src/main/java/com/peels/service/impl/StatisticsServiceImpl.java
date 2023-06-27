package com.peels.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.peels.dto.*;
import com.peels.entity.Statistics;
import com.peels.mapper.GridCityMapper;
import com.peels.mapper.GridProvinceMapper;
import com.peels.mapper.StatisticsMapper;
import com.peels.service.IStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.vo.PageResponseVo;
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
    public PageResponseVo<Statistics> listStatisticsPage(StatisticsPageRequestDto statisticsPageRequestDto) {
//        PageResponseVo<Statistics> response = new PageResponseVo<>();
//
//        // 获取总行数
//        int totalRow = statisticsMapper.getStatisticsCount(statisticsPageRequestDto);
//        response.setTotalRow(totalRow);
//
//        // 如果总行数为0，那么直接返回
//        if (totalRow == 0) {
//            return response;
//        }
//
//        // 计算总页数
//        int totalPageNum = 0;
//        if (totalRow % statisticsPageRequestDto.getMaxPageNum() == 0) {
//            totalPageNum = totalRow / statisticsPageRequestDto.getMaxPageNum();
//        } else {
//            totalPageNum = totalRow / statisticsPageRequestDto.getMaxPageNum() + 1;
//        }
//        response.setTotalPageNum(totalPageNum);
//
//        // 计算上一页和下一页
//        int pageNum = statisticsPageRequestDto.getPageNum();
//        response.setPreNum(pageNum);
//        response.setNextNum(pageNum);
//        if (pageNum > 1) {
//            response.setPreNum(pageNum - 1);
//        }
//        if (pageNum < totalPageNum) {
//            response.setNextNum(pageNum + 1);
//        }
//
//        // 计算开始查询记录数
//        statisticsPageRequestDto.setBeginNum((pageNum - 1) * statisticsPageRequestDto.getMaxPageNum());
//
//        // 查询业务数据
//        List<Statistics> list = statisticsMapper.listStatisticsPage(statisticsPageRequestDto);
//        // 给返回值填充余下数据
//        response.setPageNum(pageNum);
//        response.setMaxPageNum(statisticsPageRequestDto.getMaxPageNum());
//        response.setList(list);
//
//        return response;
        return null;
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
