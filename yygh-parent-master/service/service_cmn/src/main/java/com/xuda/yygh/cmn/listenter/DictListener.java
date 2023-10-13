package com.xuda.yygh.cmn.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xuda.yygh.cmn.mapper.DictMapper;
import com.xuda.yygh.model.cmn.Dict;
import com.xuda.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @author ：程序员徐大大
 * @description：读取Excel文件的监听器
 * @date ：2022-04-06 20:30
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
    //调入数据库,使用有参构造传入
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //一行一行的读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        //调用方法添加到数据库
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
