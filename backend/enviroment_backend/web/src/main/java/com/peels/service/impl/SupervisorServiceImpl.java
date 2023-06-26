package com.peels.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
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
 *  服务实现类
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
        if(StrUtil.isBlank(supervisor.getTelId())){
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }

//        Supervisor one = this.lambdaQuery()
//                .eq(Supervisor::getTelId, supervisor.getTelId()).one();
        Supervisor one = this.getById(supervisor.getTelId());
        System.out.println(JSON.toJSONString(one));
        if(one==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }else{
            one.setPassword("");
            return ResponseResult.okResult(one);
        }

    }

    @Override
    public ResponseResult<?> saveSupervisor(Supervisor supervisor) {
        System.out.println(JSON.toJSONString(supervisor));
        if(StrUtil.isBlank(supervisor.getTelId())||StrUtil.isBlank(supervisor.getPassword())){
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }
        this.saveOrUpdate(supervisor);
        return ResponseResult.okResult();

    }
}
