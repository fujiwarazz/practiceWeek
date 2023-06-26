package com.peels.service;

import com.peels.entity.Aqi;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.entity.AqiFeedback;
import com.peels.utils.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IAqiService extends IService<Aqi> {

    ResponseResult<?> getAqiList();

    ResponseResult<?> saveFeedBack(AqiFeedback aqiFeedback);
}
