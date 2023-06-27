package com.peels.controller;


import com.peels.entity.GridMember;
import com.peels.entity.Statistics;
import com.peels.service.IGridMemberService;
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
@RequestMapping("/gridMember")
public class GridMemberController {

    @Resource
    IGridMemberService iGridMemberService;

    @PostMapping("/getGridMemberByCodeByPass")
    public ResponseResult<?> getGridMemberByCodeByPass(@RequestBody GridMember gridMember) {
        return iGridMemberService.getGridMemberByCodeByPass(gridMember);
    }

    @PostMapping("/listGridMemberByProvinceId")
    public List<GridMember> listGridMemberByProvinceId(@RequestBody GridMember gridMember) {
        return iGridMemberService.listGridMemberByProvinceId(gridMember);
    }

}

