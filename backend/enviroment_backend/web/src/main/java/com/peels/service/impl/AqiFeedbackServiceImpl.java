package com.peels.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.RegexPool;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peels.dto.AfPageRequestDto;
import com.peels.dto.PageRequestDto;
import com.peels.entity.*;
import com.peels.mapper.*;
import com.peels.service.IAqiFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.vo.AqiDetailVo;
import com.peels.vo.PageResponseVo;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Service
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

    //TODO:修改
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer updateAqiFeedBackAssign(AqiFeedback aqiFeedback) {
        if (StrUtil.isBlank(aqiFeedback.getAssignDate())
                || aqiFeedback.getGmId() == null
                || StrUtil.isBlank(aqiFeedback.getAssignTime())
                || aqiFeedback.getState() == null
                || aqiFeedback.getAfId() == null) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }

        boolean update = this.lambdaUpdate().eq(AqiFeedback::getAfId, aqiFeedback.getAfId())
                .set(AqiFeedback::getGmId, aqiFeedback.getGmId())
                .set(AqiFeedback::getAssignDate, aqiFeedback.getAssignDate())
                .set(AqiFeedback::getState, aqiFeedback.getState())
                .set(AqiFeedback::getAssignTime, aqiFeedback.getAssignTime()).update(aqiFeedback);

        return update ? 1 : 0;
    }

    @Override
    @SuppressWarnings("all")
    public List<AqiFeedback> listAqiFeedBackByGmId(AqiFeedback aqiFeedback) {

        List<AqiFeedback> list = this.lambdaQuery()
                .eq(AqiFeedback::getGmId, aqiFeedback.getGmId())
                .orderByDesc(AqiFeedback::getAfDate, AqiFeedback::getAfTime).list();

        return list;
    }

    @Override
    public Integer updateAqiFeedbackState(AqiFeedback aqiFeedback) {
        if (aqiFeedback.getState() == null) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }
        boolean update = this.lambdaUpdate().eq(AqiFeedback::getAfId, aqiFeedback.getAfId())
                .set(AqiFeedback::getState, aqiFeedback.getState()).update();
        return update ? 1 : 0;
    }

    @Override
    @SuppressWarnings("all")
    public PageResponseVo<AqiDetailVo> listAqiFeedBackPage(AfPageRequestDto afPageRequestDto) {

        afPageRequestDto.setPageNum((afPageRequestDto.getPageNum() <= 1 ? 0 : afPageRequestDto.getPageNum() - 1) * afPageRequestDto.getMaxPageNum());


        List<AqiDetailVo> aqiDetailVos = aqiFeedbackMapper.listAqiFeedBackPage(afPageRequestDto);


        Integer count = aqiFeedbackMapper.getPageTotal(afPageRequestDto);

        PageResponseVo<AqiDetailVo> aqiDetailVoPageResponseVo = new PageResponseVo<>();
        aqiDetailVoPageResponseVo.setList(aqiDetailVos);


        aqiDetailVoPageResponseVo.setPageNum(aqiDetailVos.size() % afPageRequestDto.getMaxPageNum());
        aqiDetailVoPageResponseVo.setBeginNum(afPageRequestDto.getBeginNum());

        aqiDetailVoPageResponseVo.setTotalRow(count);
        aqiDetailVoPageResponseVo.setNextNum((afPageRequestDto.getPageNum() * afPageRequestDto.getMaxPageNum()>=count?afPageRequestDto.getPageNum()+1:afPageRequestDto.getPageNum()));
        aqiDetailVoPageResponseVo.setPreNum(afPageRequestDto.getPageNum()<=1 ? 1:afPageRequestDto.getPageNum()-1);
        aqiDetailVoPageResponseVo.setTotalPageNum(count % afPageRequestDto.getMaxPageNum() == 0 ? count / afPageRequestDto.getMaxPageNum() : count / afPageRequestDto.getMaxPageNum() + 1);
        aqiDetailVoPageResponseVo.setMaxPageNum(afPageRequestDto.getMaxPageNum());

        return aqiDetailVoPageResponseVo;
    }

    //TODO:修改
    @Override
    @Transactional
    public Integer saveFeedBack(AqiFeedback aqiFeedback) {
        if (StrUtil.isBlank(aqiFeedback.getTelId())
                || aqiFeedback.getProvinceId() == null
                || aqiFeedback.getCityId() == null
                || aqiFeedback.getGmId() == null
                || aqiFeedback.getInformation() == null) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }

        if (!aqiFeedback.getTelId().matches(RegexPool.TEL)) {
            throw new RuntimeException(AppHttpCodeEnum.PARAM_INVALID.getErrorMessage());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String[] date = format.split(" ");

        try {
            aqiFeedback.setAfDate(date[0]);
            aqiFeedback.setAfTime(date[1]);
            aqiFeedback.setState(0);

            return aqiFeedbackMapper.insert(aqiFeedback);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public List<AqiFeedback> getAqiList(AqiFeedback aqiFeedback) {
        List<AqiFeedback> list = this.lambdaQuery().eq(AqiFeedback::getTelId, aqiFeedback.getTelId())
                .orderByDesc(AqiFeedback::getAfTime).list();
        return list;
    }

    @Override
    public AqiFeedback getAqiFeedbackById(AqiFeedback aqiFeedback) {
        if (aqiFeedback.getAfId() == null) {
            return null;
        }
        AqiFeedback byId = this.getById(aqiFeedback.getAfId());
        if (byId == null) {
            return null;
        } else {
            return byId;
        }
    }
}
