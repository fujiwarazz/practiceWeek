package com.peels.service;

import com.peels.dto.AfPageRequestDto;
import com.peels.entity.AqiFeedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.utils.ResponseResult;
import com.peels.vo.PageResponseVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IAqiFeedbackService extends IService<AqiFeedback> {

    ResponseResult<?> saveFeedBack(AqiFeedback aqiFeedback);

    ResponseResult<?> getAqiList();

    ResponseResult<?> getAqiFeedbackById(AqiFeedback aqiFeedback);

    Integer updateAqiFeedBackAssign(AqiFeedback aqiFeedback);

    List<AqiFeedback> listAqiFeedBackByGmId(AqiFeedback aqiFeedback);

    Integer updateAqiFeedbackState(AqiFeedback aqiFeedback);

    PageResponseVo<AqiFeedback> listAqiFeedBackPage(AfPageRequestDto afPageRequestDto);
}
