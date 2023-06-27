package com.peels.controller;


import com.peels.dto.AfPageRequestDto;
import com.peels.entity.Aqi;
import com.peels.entity.AqiFeedback;
import com.peels.service.IAqiFeedbackService;
import com.peels.utils.ResponseResult;
import com.peels.vo.PageResponseVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
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
    public Integer updateAqiFeedbackAssign(@RequestBody AqiFeedback aqiFeedback){
        return aqiFeedbackService.updateAqiFeedBackAssign(aqiFeedback);
    }

    @PostMapping("/listAqiFeedbackByGmId")
    public List<AqiFeedback>  listAqiFeedbackByGmId(AqiFeedback aqiFeedback) {
        return aqiFeedbackService.listAqiFeedBackByGmId(aqiFeedback);
    }

    @PostMapping("/updateAqiFeedbackState")
    public Integer updateAqiFeedbackState(@RequestBody AqiFeedback aqiFeedback){
        return aqiFeedbackService.updateAqiFeedbackState(aqiFeedback);
    }

    @PostMapping("/listAqiFeedbackPage")
    public PageResponseVo<AqiFeedback> listAqiFeedBackPage(@RequestBody AfPageRequestDto afPageRequestDto){
        return aqiFeedbackService.listAqiFeedBackPage(afPageRequestDto);

    }
}

