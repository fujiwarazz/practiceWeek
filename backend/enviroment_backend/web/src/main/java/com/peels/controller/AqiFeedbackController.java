package com.peels.controller;


import com.peels.dto.*;
import com.peels.entity.AqiFeedback;
import com.peels.service.IAqiFeedbackService;
import com.peels.utils.ResponseResult;
import com.peels.vo.AqiDetailVo;
import com.peels.vo.PageResponseVo;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/aqiFeedback")
public class AqiFeedbackController {

    @Resource
    private IAqiFeedbackService aqiFeedbackService;

    @PostMapping("/saveAqiFeedback")
    public ResponseResult<?> saveFeedBack(@RequestBody AqiFeedBackDto aqiFeedback){
        return aqiFeedbackService.saveFeedBack(aqiFeedback);
    }

    @PostMapping("/listAqiFeedbackByTelId")
    public List<AqiFeedback> getAqiFeedbackByTelId(@RequestBody TelIdDto  telId){
        return aqiFeedbackService.getAqiList(telId);
    }

    @PostMapping("/getAqiFeedbackById")
    public AqiFeedback getAqiFeedbackById(@RequestBody AfIdDto afId){
        return aqiFeedbackService.getAqiFeedbackById(afId);
    }
    @PostMapping("/updateAqiFeedbackAssign")
    public ResponseResult<?> updateAqiFeedbackAssign(@RequestBody UpdateFeedBackAssignDto updateFeedBackAssignDto){
        return aqiFeedbackService.updateAqiFeedBackAssign(updateFeedBackAssignDto);
    }

    @PostMapping("/listAqiFeedbackByGmId")
    public List<AqiFeedback>  listAqiFeedbackByGmId(@RequestBody GmIdDto gmId) {
        return aqiFeedbackService.listAqiFeedBackByGmId(gmId);
    }

    @PostMapping("/updateAqiFeedbackState")
    public ResponseResult<?> updateAqiFeedbackState(@RequestBody UpdateFeedBackStateDto updateFeedBackStateDto){
        return aqiFeedbackService.updateAqiFeedbackState(updateFeedBackStateDto);
    }

    @PostMapping("/listAqiFeedbackPage")
    public PageResponseVo<AqiDetailVo> listAqiFeedBackPage(@RequestBody AfPageRequestDto afPageRequestDto){
        return aqiFeedbackService.listAqiFeedBackPage(afPageRequestDto);

    }
}

