package com.peels.service;

import com.peels.dto.*;
import com.peels.entity.AqiFeedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.utils.ResponseResult;
import com.peels.vo.AqiDetailVo;
import com.peels.vo.FeedBackVo;
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

    ResponseResult<?> saveFeedBack(AqiFeedBackDto aqiFeedback);

    List<FeedBackVo> getAqiList(TelIdDto telId);

    AqiFeedback getAqiFeedbackById(AfIdDto afId);

    ResponseResult<?> updateAqiFeedBackAssign(UpdateFeedBackAssignDto updateFeedBackAssignDto);

    List<AqiFeedback> listAqiFeedBackByGmId(GmIdDto gmId);

    ResponseResult<?> updateAqiFeedbackState(UpdateFeedBackStateDto updateFeedBackStateDto);

    PageResponseVo<AqiDetailVo> listAqiFeedBackPage(AfPageRequestDto afPageRequestDto);
}
