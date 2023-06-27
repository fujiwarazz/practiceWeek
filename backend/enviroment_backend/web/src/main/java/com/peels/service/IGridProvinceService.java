package com.peels.service;

import com.peels.entity.GridProvince;
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
public interface IGridProvinceService extends IService<GridProvince> {


    List<GridProvince> listGridProvinceAll();
}
