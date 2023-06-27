package com.peels.controller;


import com.peels.entity.Aqi;
import com.peels.entity.AqiFeedback;
import com.peels.service.IAqiService;
import com.peels.utils.ResponseResult;
import org.checkerframework.checker.units.qual.A;
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
@RequestMapping("/aqi")
public class AqiController {

    @Resource
    private IAqiService aqiService;

    @PostMapping("/listAqiAll")
    public List<Aqi> getAqiList(){
        return aqiService.getAqiList();
    }

}

