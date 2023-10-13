package com.xuda.yygh.common.result;

import com.xuda.yygh.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;
/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-14 21:21
 */
public class AuthContextHolder {
    //获取当前用户id
    public static Long getUserId(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }

    //获取当前用户名称
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        String userName = JwtHelper.getUserName(token);
        return userName;
    }
}

