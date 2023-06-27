package com.peels.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peels.entity.GridProvince;
import com.peels.mapper.GridProvinceMapper;
import com.peels.service.IGridProvinceService;
import com.peels.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GridProvinceServiceImpl extends ServiceImpl<GridProvinceMapper, GridProvince> implements IGridProvinceService {

    @Resource
    private GridProvinceMapper gridProvinceMapper;
    @Override
    public List<GridProvince> listGridProvinceAll() {
        return gridProvinceMapper.selectList(null);
    }
}
