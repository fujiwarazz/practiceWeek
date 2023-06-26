package com.peels.service;

import com.peels.entity.Supervisor;
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
public interface ISupervisorService extends IService<Supervisor> {

    ResponseResult<?> getSupervisorById(Supervisor supervisor);

    ResponseResult<?> saveSupervisor(Supervisor supervisor);
}
