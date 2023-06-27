package com.peels.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.entity.GridCity;
import com.peels.mapper.GridCityMapper;
import com.peels.service.IGridCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author peelsannaw
 * @create 26/06/2023 14:32
 */
@Service
public class GridCityServiceImpl extends ServiceImpl<GridCityMapper, GridCity> implements IGridCityService {

    @Resource
    private GridCityMapper gridCityMapper;
    @Override
    public List<GridCity> listGridCityByProvinceId(GridCity gridCity) {
        QueryWrapper<GridCity> qw = new QueryWrapper<>();
        qw.eq("province_id", gridCity.getProvinceId());
        return gridCityMapper.selectList(qw);
    }
}
