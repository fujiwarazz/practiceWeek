package com.peels.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.peels.annotation.Prevent;
import com.peels.entity.Supervisor;
import com.peels.mapper.SupervisorMapper;
import com.peels.service.ISupervisorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.utils.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@Service
public class SupervisorServiceImpl extends ServiceImpl<SupervisorMapper, Supervisor> implements ISupervisorService {

    @Resource
    private SupervisorMapper supervisorMapper;

    @Override
    public ResponseResult<?> getSupervisorById(Supervisor supervisor) {

        System.out.println(JSON.toJSONString(supervisor));
        if (StrUtil.isBlank(supervisor.getTelId())) {
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }

//        Supervisor one = this.lambdaQuery()
//                .eq(Supervisor::getTelId, supervisor.getTelId()).one();
        Supervisor one = this.getById(supervisor.getTelId());
        System.out.println(JSON.toJSONString(one));
        if (one == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else {
            one.setPassword(null);
            return ResponseResult.okResult(one);
        }

    }

    @Override
    public int saveSupervisor(Supervisor supervisor) {
        if (StrUtil.isBlank(supervisor.getTelId()) || StrUtil.isBlank(supervisor.getPassword())) {
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }
        this.saveOrUpdate(supervisor);
        return 200;

    }

    @Override
    public ResponseResult<?> getSupervisorByIdByPass(Supervisor supervisor) {
        if (StrUtil.isBlank(supervisor.getTelId()) || StrUtil.isBlank(supervisor.getPassword())) {
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }
        Supervisor one = supervisorMapper.selectOne(new QueryWrapper<Supervisor>()
                .eq("tel_id", supervisor.getTelId())
                .eq("password", supervisor.getPassword()));

        if (one == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else {
             one.setPassword(null);
            return ResponseResult.okResult(one);
        }


    }
}
