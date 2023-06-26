package com.peels.service.impl;

import cn.hutool.core.util.StrUtil;
import com.peels.entity.AqiFeedback;
import com.peels.mapper.AqiFeedbackMapper;
import com.peels.service.IAqiFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.utils.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
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
    public ResponseResult<?> updateAqiFeedBackAssign(AqiFeedback aqiFeedback) {
        return null;
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
        if(list.size()==0){
            return ResponseResult.okResult(200,"查询不到数据");
        }
        else return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult<?> getAqiFeedbackById(AqiFeedback aqiFeedback) {
        if(aqiFeedback.getTelId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        AqiFeedback byId = this.getById(aqiFeedback.getTelId());
        if(byId==null){
            return ResponseResult.okResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }else {
            return ResponseResult.okResult(byId);
        }
    }
}
