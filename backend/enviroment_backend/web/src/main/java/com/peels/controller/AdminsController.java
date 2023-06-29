package com.peels.controller;


import cn.hutool.json.JSON;
import com.peels.dto.AdminLoginDto;
import com.peels.entity.Admins;
import com.peels.service.IAdminsService;


import com.peels.utils.*;
import com.peels.vo.AdminsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.ws.rs.POST;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author peelsannaw
 * @since 2023-06-26
 */
@RestController
@EnableSwagger2
@Slf4j
@RequestMapping("/admins")
public class AdminsController {

    @Resource
    IAdminsService adminsService;

    @PostMapping("/getAdminsByCode")
    public AdminsVo getAdminsByCode(@RequestBody AdminLoginDto admins) {
        return adminsService.getAdminByCode(admins);
    }
}

