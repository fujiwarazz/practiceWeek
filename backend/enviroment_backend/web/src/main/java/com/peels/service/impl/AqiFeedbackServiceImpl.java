package com.peels.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.RegexPool;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.peels.dto.*;
import com.peels.entity.*;
import com.peels.mapper.*;
import com.peels.service.IAqiFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.utils.FunctionUtil;
import com.peels.utils.ResponseResult;
import com.peels.vo.AqiDetailVo;
import com.peels.vo.FeedBackVo;
import com.peels.vo.PageResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Service
@Slf4j
public class AqiFeedbackServiceImpl extends ServiceImpl<AqiFeedbackMapper, AqiFeedback> implements IAqiFeedbackService {

    @Resource
    private AqiFeedbackMapper aqiFeedbackMapper;

    @Resource
    private GridMemberMapper gridMemberMapper;

    @Resource
    private SupervisorMapper supervisorMapper;

    @Resource
    private GridCityMapper gridCityMapper;

    @Resource
    private GridProvinceMapper gridProvinceMapper;

    @Resource
    AqiMapper aqiMapper;



    @Override
    @Transactional(rollbackFor = SQLException.class)
    public ResponseResult<?> updateAqiFeedBackAssign(UpdateFeedBackAssignDto updateFeedBackAssignDto) {

        if (updateFeedBackAssignDto.getGmId() == null
                || updateFeedBackAssignDto.getState() == null
                || updateFeedBackAssignDto.getAfId() == null) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            String[] date = format.split(" ");


            boolean update = this.lambdaUpdate()
                    .eq(AqiFeedback::getAfId, updateFeedBackAssignDto.getAfId())
                    .set(AqiFeedback::getGmId, updateFeedBackAssignDto.getGmId())
                    .set(AqiFeedback::getAssignDate,date[0])
                    .set(AqiFeedback::getState, 1)
                    .set(AqiFeedback::getAssignTime, date[1]).update();

//            GridMember gridMember = gridMemberMapper.selectById(updateFeedBackAssignDto.getGmId());
//
//            gridMemberMapper.update(gridMember,new LambdaUpdateWrapper<GridMember>()
//                    .eq(GridMember::getGmId,gridMember.getGmId())
//                    .set(GridMember::getState,updateFeedBackAssignDto.getState()));


            return update ? ResponseResult.okResult("更新成功") : ResponseResult.errorResult(400, "更新失败");
        } catch (Exception e) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    @SuppressWarnings("all")
    public List<AqiFeedback> listAqiFeedBackByGmId(GmIdDto gmId) {

        List<AqiFeedback> list = this.<AqiFeedback>lambdaQuery()
                .eq(AqiFeedback::getGmId, gmId.getGmId())
                .orderByDesc(AqiFeedback::getAfDate, AqiFeedback::getAfTime).list();
        return list;
    }

    @Override
    public ResponseResult<?> updateAqiFeedbackState(UpdateFeedBackStateDto dto) {
        if (dto.getState() == null) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }
        boolean update = this.lambdaUpdate().eq(AqiFeedback::getAfId, dto.getAfId())
                .set(AqiFeedback::getState, dto.getState()).update();
        return update ? ResponseResult.okResult("更新成功") : ResponseResult.errorResult(400, "更新失败");
    }

    @Override
    @SuppressWarnings("all")
    public PageResponseVo<AqiDetailVo> listAqiFeedBackPage(AfPageRequestDto afPageRequestDto) {
        afPageRequestDto.setPageNum((afPageRequestDto.getPageNum() <= 1 ? 0 : afPageRequestDto.getPageNum() - 1) * afPageRequestDto.getMaxPageNum());
        List<AqiDetailVo> aqiDetailVos = aqiFeedbackMapper.listAqiFeedBackPage(afPageRequestDto);
        Set<Integer>set = new HashSet<>();

//        aqiDetailVos.stream()
//                .filter(FunctionUtil.distinctByKey(AqiDetailVo::getAfId))
//                .collect(Collectors.toList());

        Integer count = aqiFeedbackMapper.getPageTotal(afPageRequestDto);

        PageResponseVo<AqiDetailVo> aqiDetailVoPageResponseVo = new PageResponseVo<>();
        aqiDetailVoPageResponseVo.setList(aqiDetailVos);


        aqiDetailVoPageResponseVo.setPageNum(aqiDetailVos.size() % afPageRequestDto.getMaxPageNum());
        aqiDetailVoPageResponseVo.setBeginNum(afPageRequestDto.getBeginNum());

        aqiDetailVoPageResponseVo.setTotalRow(count);
        aqiDetailVoPageResponseVo.setNextNum((afPageRequestDto.getPageNum() * afPageRequestDto.getMaxPageNum() >= count ? afPageRequestDto.getPageNum() + 1 : afPageRequestDto.getPageNum()));
        aqiDetailVoPageResponseVo.setPreNum(afPageRequestDto.getPageNum() <= 1 ? 1 : afPageRequestDto.getPageNum() - 1);
        aqiDetailVoPageResponseVo.setTotalPageNum(count % afPageRequestDto.getMaxPageNum() == 0 ? count / afPageRequestDto.getMaxPageNum() : count / afPageRequestDto.getMaxPageNum() + 1);
        aqiDetailVoPageResponseVo.setMaxPageNum(afPageRequestDto.getMaxPageNum());

        return aqiDetailVoPageResponseVo;
    }


    @Override
    @Transactional
    public ResponseResult<?> saveFeedBack(AqiFeedBackDto aqiFeedback) {
        if (StrUtil.isBlank(aqiFeedback.getTelId())
                || aqiFeedback.getProvinceId() == null
                || aqiFeedback.getCityId() == null
                || aqiFeedback.getInformation() == null) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }

        if (!aqiFeedback.getTelId().matches(RegexPool.MOBILE)) {
            throw new RuntimeException("电话号码格式错误");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String[] date = format.split(" ");

        try {
            AqiFeedback feedback = new AqiFeedback();

            BeanUtil.copyProperties(aqiFeedback, feedback);
            feedback.setState(0);
            feedback.setAfDate(date[0]);
            feedback.setAfTime(date[1]);
            this.save(feedback);
            return ResponseResult.okResult("插入数据成功");
        } catch (Exception e) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
    }


    @Override
    public List<FeedBackVo> getAqiList(TelIdDto telId) {

        List<FeedBackVo> aqiDetailVos = aqiFeedbackMapper.getFeedBackVos(telId.getTelId());

        return aqiDetailVos;


    }

    @Override
    public AqiFeedback getAqiFeedbackById(AfIdDto afId) {
        if (afId == null) {
            return null;
        }
        AqiFeedback byId = this.getById(afId.getAfId());
        if (byId == null) {
            return null;
        } else {
            return byId;
        }
    }
}
