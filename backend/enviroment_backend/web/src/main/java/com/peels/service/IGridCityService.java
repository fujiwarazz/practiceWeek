package com.peels.service;

import com.peels.entity.GridCity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IGridCityService extends IService<GridCity> {

    List<GridCity> listGridCityByProvinceId(GridCity gridCity);
}
