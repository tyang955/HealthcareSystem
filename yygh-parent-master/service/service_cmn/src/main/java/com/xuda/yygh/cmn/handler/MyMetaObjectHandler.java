package com.xuda.yygh.cmn.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 14:44
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //mp执行添加操作，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
   //     this.setFieldValByName("version",1,metaObject);
        this.setFieldValByName("isDeleted",0,metaObject);
    }
    //mp执行修改操作，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
