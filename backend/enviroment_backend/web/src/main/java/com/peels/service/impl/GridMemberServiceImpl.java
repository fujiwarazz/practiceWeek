package com.peels.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.entity.GridMember;
import com.peels.entity.Supervisor;
import com.peels.mapper.GridMemberMapper;
import com.peels.service.IGridMemberService;
import com.peels.utils.AppHttpCodeEnum;
import com.peels.utils.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author peelsannaw
 * @create 26/06/2023 14:33
 */
@Service
public class GridMemberServiceImpl extends ServiceImpl<GridMemberMapper, GridMember> implements IGridMemberService {

    @Resource
    private GridMemberMapper gridMemberMapper;
    @Override
    public ResponseResult<?> getGridMemberByCodeByPass(GridMember gridMember) {
        if (StrUtil.isBlank(gridMember.getGmCode()) || StrUtil.isBlank(gridMember.getPassword())) {
            throw new RuntimeException(AppHttpCodeEnum.LOGIN_PARAMS_ERROR.getErrorMessage());
        }
        GridMember one = gridMemberMapper.selectOne(new QueryWrapper<GridMember>()
                .eq("gm_code", gridMember.getGmCode())
                .eq("password", gridMember.getPassword()));

        if (one == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else {
            one.setPassword(null);
            return ResponseResult.okResult(one);
        }

    }

    @Override
    public List<GridMember> listGridMemberByProvinceId(GridMember gridMember) {

        List<GridMember> one = gridMemberMapper.selectList(new QueryWrapper<GridMember>()
                .eq("province_id", gridMember.getProvinceId())
                .eq("city_id", gridMember.getCityId())
                .eq("state",0)
        );


//        QueryWrapper<GridMember> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("province_id", gridMember.getProvinceId());
//        queryWrapper.eq("city_id", gridMember.getCityId());
//        queryWrapper.eq("state", gridMember.getState());
//        return gridMemberMapper.selectList(queryWrapper);
        System.out.println(one);
        return one;
    }
}
