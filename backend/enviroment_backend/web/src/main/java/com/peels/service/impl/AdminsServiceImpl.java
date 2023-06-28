package com.peels.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.peels.annotation.Prevent;
import com.peels.entity.Admins;
import com.peels.mapper.AdminsMapper;
import com.peels.service.IAdminsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.vo.AdminsVo;
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
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements IAdminsService {

    @Resource
    private AdminsMapper adminsMapper;

    @Override
    public AdminsVo getAdminByCode(Admins admins) {

        System.out.println(JSON.toJSONString(admins));


        if(StrUtil.isBlank(admins.getAdminCode())|| StrUtil.isBlank(admins.getPassword())){
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }

        Admins one = this.lambdaQuery()
                .eq(Admins::getAdminCode, admins.getAdminCode())
                .eq(Admins::getPassword, admins.getPassword()).one();

        AdminsVo adminsVo = BeanUtil.copyProperties(one, AdminsVo.class);

        if(one==null){
            return null;
        }else{
            return adminsVo;
        }
    }
}
