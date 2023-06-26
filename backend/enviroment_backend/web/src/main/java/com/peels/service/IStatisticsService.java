package com.peels.service;

import com.peels.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.utils.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IStatisticsService extends IService<Statistics> {

    String getCityCoverage();
}
