package com.xuda.yygh.common.exception;

import com.xuda.yygh.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 19:30
 */
@Controller
@ResponseBody //将结构用json输出
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result error (Exception e) {
        e.printStackTrace();
        return Result.fail();
    }
    @ExceptionHandler(YyghException.class)
    public Result error(YyghException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
