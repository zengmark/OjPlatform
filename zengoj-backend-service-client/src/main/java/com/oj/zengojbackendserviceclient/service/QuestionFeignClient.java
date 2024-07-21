package com.oj.zengojbackendserviceclient.service;

import com.oj.zengojbackendmodel.model.entity.Question;
import com.oj.zengojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
* @author 13123
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2023-10-14 15:23:58
*/
@FeignClient(name = "zengoj-backend-question-service", path = "/api/question/inner")
public interface QuestionFeignClient {

    /**
     * questionService.getById(questionId)
     * questionSubmitService.getById(questionSubmitId)
     * questionSubmitService.updateById(questionSubmitUpdate)
     */

    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);

    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);

    @PostMapping("/question_submit/update/")
    boolean updateQuestionSubmitById(QuestionSubmit questionSubmit);
}
