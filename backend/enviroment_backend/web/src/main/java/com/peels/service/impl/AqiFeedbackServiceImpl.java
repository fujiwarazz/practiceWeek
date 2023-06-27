package com.peels.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peels.dto.AfPageRequestDto;
import com.peels.dto.PageRequestDto;
import com.peels.entity.AqiFeedback;
import com.peels.mapper.AqiFeedbackMapper;
import com.peels.service.IAqiFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.utils.ResponseResult;
import com.peels.vo.PageResponseVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
        UpdateWrapper<AqiFeedback> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("gm_id", aqiFeedback.getGmId());
        updateWrapper.set("assign_date", aqiFeedback.getAssignDate());
        updateWrapper.set("assign_time", aqiFeedback.getAssignTime());
        updateWrapper.set("state", aqiFeedback.getState());
        updateWrapper.eq("af_id", aqiFeedback.getAfId());

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
                .eq(AqiFeedback::getState, aqiFeedback.getState())
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
    public PageResponseVo<AqiFeedback> listAqiFeedBackPage(AfPageRequestDto afPageRequestDto) {
//        private Integer provinceId;
//        private Integer cityId;
//        private Integer estimatedGrade;
//        private String afDate;
//        private Integer state;
        Page<AqiFeedback> page = this.page(new Page<AqiFeedback>(afPageRequestDto.getPageNum(), afPageRequestDto.getMaxPageNum())
                , new LambdaUpdateWrapper<AqiFeedback>().eq(afPageRequestDto.getProvinceId() != null, AqiFeedback::getProvinceId, afPageRequestDto.getProvinceId())
                        .eq(afPageRequestDto.getCityId() != null, AqiFeedback::getCityId, afPageRequestDto.getCityId())
                        .like(afPageRequestDto.getEstimatedGrade() != null, AqiFeedback::getEstimatedGrade, afPageRequestDto.getEstimatedGrade())
                        .eq(StrUtil.isNotBlank(afPageRequestDto.getAfDate()), AqiFeedback::getAfDate, afPageRequestDto.getAfDate())
                        .eq(afPageRequestDto.getState() != null, AqiFeedback::getState, afPageRequestDto.getState())
                        .orderByDesc(AqiFeedback::getAfDate));


        PageResponseVo<AqiFeedback> pageResponseVo = new PageResponseVo<>();
        pageResponseVo.setList(page.getRecords());
        pageResponseVo.setPageNum((int) page.getCurrent());
        pageResponseVo.setBeginNum(afPageRequestDto.getBeginNum());
        pageResponseVo.setTotalRow((int) page.getTotal());
        pageResponseVo.setNextNum(page.getCurrent()>page.getTotal()?(int) page.getCurrent()+1: (int) page.getTotal());
        pageResponseVo.setPreNum(page.getCurrent()==afPageRequestDto.getBeginNum()?afPageRequestDto.getBeginNum(): (int) (page.getCurrent() - 1));
        pageResponseVo.setTotalPageNum((int) page.getPages());
        pageResponseVo.setMaxPageNum(afPageRequestDto.getMaxPageNum());


        return pageResponseVo;
    }

    @Override
    @Transactional
    public ResponseResult<?> saveFeedBack(AqiFeedback aqiFeedback) {
        try {
            if (StrUtil.isBlank(aqiFeedback.getTelId())
                    || aqiFeedback.getProvinceId() == null
                    || aqiFeedback.getCityId() == null
                    || aqiFeedback.getGmId() == null
                    || aqiFeedback.getInformation() == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            String[] date = format.split(" ");

            aqiFeedback.setAfDate(date[0]);
            aqiFeedback.setAfTime(date[1]);

            int flag = aqiFeedbackMapper.insert(aqiFeedback);

            if (flag != 0) {
                return ResponseResult.okResult();
            } else return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult<?> getAqiList() {
        List<AqiFeedback> list = this.lambdaQuery().orderByDesc(AqiFeedback::getAfTime).list();
        if (list.size() == 0) {
            return ResponseResult.okResult(200, "查询不到数据");
        } else return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult<?> getAqiFeedbackById(AqiFeedback aqiFeedback) {
        if (aqiFeedback.getTelId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        AqiFeedback byId = this.getById(aqiFeedback.getTelId());
        if (byId == null) {
            return ResponseResult.okResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else {
            return ResponseResult.okResult(byId);
        }
    }
}
