package com.xuda.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author ：程序员徐大大
 * @description：这个类是在进行读去excel表格数据时，需要设置监听器
 * @date ：2022-04-06 19:50
 */
public class ExcelListener extends AnalysisEventListener<UserData> {
    /**
     * 一行一行的读取数据，表格的头不读，从第二行开始读
     * @param userData
     * @param analysisContext
     */
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }

    /**
     * 读取表头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息"+headMap);
    }
    /**
     * 读取之后执行
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
