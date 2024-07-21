package com.oj.zengojbackendjudgeservice.judge.codesandbox;

import com.oj.zengojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.oj.zengojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
