package com.peels.service;

import com.peels.dto.AdminLoginDto;
import com.peels.entity.Admins;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.vo.AdminsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IAdminsService extends IService<Admins> {

    AdminsVo getAdminByCode(AdminLoginDto admins);
}
