package com.peels.service;

import com.peels.entity.GridMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peels.utils.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
public interface IGridMemberService extends IService<GridMember> {

    ResponseResult<?> getGridMemberByCodeByPass(GridMember gridMember);

    List<GridMember> listGridMemberByProvinceId(GridMember gridMember);
}
