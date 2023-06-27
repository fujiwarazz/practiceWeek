package com.peels.controller;


import com.peels.entity.GridCity;
import com.peels.entity.GridMember;
import com.peels.service.IGridCityService;
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
@RequestMapping("/gridCity")
public class GridCityController {

    @Resource
    IGridCityService iGridCityService;

    @PostMapping("/listGridCityByProvinceId")
    public List<GridCity> listGridCityByProvinceId(@RequestBody GridCity gridCity) {
        return iGridCityService.listGridCityByProvinceId(gridCity);
    }

}

