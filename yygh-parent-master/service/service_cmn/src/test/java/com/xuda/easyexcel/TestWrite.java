package com.xuda.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-06 19:40
 */
public class TestWrite {
    public static void main(String[] args) {

        //构建数据
        ArrayList<UserData> objects = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                UserData userData = new UserData();
                userData.setUid(i);
                userData.setUsername("name"+i);
                objects.add(userData);
            }
        //设置excel文件路径
        String fileName = "E:\\JAVA\\01.xlsx";

        //调用方法实现写操作
        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(objects);
    }
}
