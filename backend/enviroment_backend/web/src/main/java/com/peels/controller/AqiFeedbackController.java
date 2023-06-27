package com.peels.controller;


import com.peels.entity.Aqi;
import com.peels.entity.AqiFeedback;
import com.peels.service.IAqiFeedbackService;
import com.peels.utils.ResponseResult;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/aqiFeedback")
public class AqiFeedbackController {
    @Resource
    private IAqiFeedbackService aqiFeedbackService;

    @PostMapping("/saveAqiFeedback")
    public ResponseResult<?> saveFeedBack(@RequestBody AqiFeedback aqiFeedback){
        return aqiFeedbackService.saveFeedBack(aqiFeedback);
    }
    @PostMapping("/listAqiFeedbackByTelId")
    public ResponseResult<?> getAqiFeedbackByTelId(){
        return aqiFeedbackService.getAqiList();
    }

    @PostMapping("/getAqiFeedbackById")
    public ResponseResult<?> getAqiFeedbackById(@RequestBody AqiFeedback aqiFeedback){
        return aqiFeedbackService.getAqiFeedbackById(aqiFeedback);
    }
    @PostMapping("/updateAqiFeedbackAssign")
    public ResponseResult<?> updateAqiFeedbackAssign(@RequestBody AqiFeedback aqiFeedback){
        return aqiFeedbackService.updateAqiFeedBackAssign(aqiFeedback);
    }
}

