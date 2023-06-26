package com.peels.service;

import com.peels.entity.AqiFeedback;
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
public interface IAqiFeedbackService extends IService<AqiFeedback> {

    ResponseResult<?> saveFeedBack(AqiFeedback aqiFeedback);

    ResponseResult<?> getAqiList();

    ResponseResult<?> getAqiFeedbackById(AqiFeedback aqiFeedback);

    ResponseResult<?> updateAqiFeedBackAssign(AqiFeedback aqiFeedback);
}
