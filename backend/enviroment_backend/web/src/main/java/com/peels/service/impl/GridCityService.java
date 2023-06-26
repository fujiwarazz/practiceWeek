package com.peels.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.entity.Aqi;
import com.peels.entity.GridCity;
import com.peels.mapper.AqiMapper;
import com.peels.mapper.GridCityMapper;
import com.peels.service.IAqiService;
import com.peels.service.IGridCityService;
import org.springframework.stereotype.Service;

/**
 * @Author peelsannaw
 * @create 26/06/2023 14:32
 */
@Service
public class GridCityService extends ServiceImpl<GridCityMapper, GridCity> implements IGridCityService {
}
