package com.xuda.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-06 19:39
 */
@Data
public class UserData {

    @ExcelProperty("用户编号")
    private int uid;

    @ExcelProperty("用户名称")
    private String username;


    //上面操作为写入
    //下面操作为读操作
    /**
     *  @ExcelProperty(value = "用户编号", index = 0) //加上下标
     *     private int uid;
     *
     *     @ExcelProperty(value = "用户名称", index = 1)
     *     private String username;
     */
}
