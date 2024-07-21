package com.oj.zengojbackendquestionservice.controller.inner;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.oj.zengojbackendcommon.annotation.AuthCheck;
import com.oj.zengojbackendcommon.common.BaseResponse;
import com.oj.zengojbackendcommon.common.DeleteRequest;
import com.oj.zengojbackendcommon.common.ErrorCode;
import com.oj.zengojbackendcommon.common.ResultUtils;
import com.oj.zengojbackendcommon.constant.UserConstant;
import com.oj.zengojbackendcommon.exception.BusinessException;
import com.oj.zengojbackendcommon.exception.ThrowUtils;
import com.oj.zengojbackendmodel.model.dto.question.*;
import com.oj.zengojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.oj.zengojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.oj.zengojbackendmodel.model.entity.Question;
import com.oj.zengojbackendmodel.model.entity.QuestionSubmit;
import com.oj.zengojbackendmodel.model.entity.User;
import com.oj.zengojbackendmodel.model.vo.QuestionSubmitVO;
import com.oj.zengojbackendmodel.model.vo.QuestionVO;
import com.oj.zengojbackendquestionservice.service.QuestionService;
import com.oj.zengojbackendquestionservice.service.QuestionSubmitService;
import com.oj.zengojbackendserviceclient.service.QuestionFeignClient;
import com.oj.zengojbackendserviceclient.service.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 题目接口
 *

 */
@RestController
@RequestMapping("/inner")
@Slf4j
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId){
        return questionService.getById(questionId);
    }

    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    }

    @PostMapping("/question_submit/update/")
    public boolean updateQuestionSubmitById(QuestionSubmit questionSubmit){
        return questionSubmitService.updateById(questionSubmit);
    }
}
