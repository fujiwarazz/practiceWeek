package com.peels.controller;


import com.peels.entity.Supervisor;
import com.peels.service.ISupervisorService;
import com.peels.utils.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
@RequestMapping("/supervisor")
public class SupervisorController {

    @Resource
    ISupervisorService supervisorService;

    @PostMapping("/getSupervisorById")
        public ResponseResult<?> getSupervisorsById(@RequestBody Supervisor supervisor) {
            return supervisorService.getSupervisorById(supervisor);
        }
    }





