package com.peels.mapper;

import com.peels.dto.AfPageRequestDto;
import com.peels.entity.AqiFeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peels.vo.AqiDetailVo;
import com.peels.vo.FeedBackVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Mapper

public interface AqiFeedbackMapper extends BaseMapper<AqiFeedback> {

    List<AqiDetailVo> listAqiFeedBackPage(AfPageRequestDto afPageRequestDto);

    Integer getPageTotal(AfPageRequestDto afPageRequestDto);

    List<FeedBackVo> getFeedBackVos(String telId);
}
