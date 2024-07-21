package com.oj.zengojbackendjudgeservice.judge;

import com.oj.zengojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.oj.zengojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.oj.zengojbackendjudgeservice.judge.strategy.JudgeContext;
import com.oj.zengojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.oj.zengojbackendmodel.model.codesandbox.JudgeInfo;
import com.oj.zengojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
