package com.peels.controller;


import com.peels.entity.GridProvince;
import com.peels.entity.Statistics;
import com.peels.service.IGridProvinceService;
import com.peels.service.impl.GridProvinceServiceImpl;
import com.peels.utils.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/gridProvince")
public class GridProvinceController {

    @Resource
    GridProvinceServiceImpl gridProvinceService;

    @PostMapping("/listGridProvinceAll")
    public List<GridProvince> listGridProvinceAll(){
        return gridProvinceService.listGridProvinceAll();
    }


}

