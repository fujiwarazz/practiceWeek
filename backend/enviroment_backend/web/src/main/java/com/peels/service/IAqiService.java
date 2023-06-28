package com.peels.service;

import com.peels.entity.Aqi;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.entity.AqiFeedback;
import com.peels.utils.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IAqiService extends IService<Aqi> {

    List<Aqi> getAqiList();

    ResponseResult<?> saveFeedBack(AqiFeedback aqiFeedback);
}
