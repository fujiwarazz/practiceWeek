package com.peels.controller;


import com.peels.entity.Statistics;
import com.peels.entity.Supervisor;
import com.peels.service.IStatisticsService;
import com.peels.utils.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    IStatisticsService supervisorService;

    @PostMapping("/getCityCoverage")
    public String getCityCoverage() {
        return supervisorService.getCityCoverage();
    }
}

