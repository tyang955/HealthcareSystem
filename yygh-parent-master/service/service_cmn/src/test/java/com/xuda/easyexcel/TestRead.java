package com.xuda.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-06 19:54
 */
public class TestRead {
    public static void main(String[] args) {
        //读取文件路径
        String fileName = "E:\\JAVA\\01.xlsx";
        //调用读取方法实现
        EasyExcel.read(fileName, UserData.class,new ExcelListener()).sheet().doRead();
    }
}
