package com.xuda.yygh.controller;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuda.yygh.common.exception.YyghException;
import com.xuda.yygh.common.result.Result;
import com.xuda.yygh.model.hosp.HospitalSet;
import com.xuda.yygh.service.HospitalSetService;
import com.xuda.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 14:28
 */

@Api(tags = "医院设置管理")  //swagger页面显示信息
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
//@CrossOrigin //允许跨域请求
public class HospitalSetController {
    //注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    //查询医院设置表里的所有信息
    @ApiOperation(value = "获取所有的医院设置信息")  //swagger页面显示信息
    @GetMapping("findAll")
    //Result对结果进行了统一的封装，可以显示不同的信息
    public Result findAllHospital(){
        //调用service中的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //逻辑删除医院设置的一条信息
    @ApiOperation(value = "逻辑删除医院设置")  //swagger页面显示信息
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //条件查询带分页
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //@RequestBody  接收前端传递给后端的json字符串中的数据，用于post请求
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page(current,limit);

        //构造查询条件
        String hosname = hospitalSetQueryVo.getHosname(); //医院名称
        String hoscode = hospitalSetQueryVo.getHoscode(); //医院编号
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(hosname)){
            wrapper.like("hosname",hosname);
        }
        if(!StringUtils.isEmpty(hoscode)){
            wrapper.eq("hoscode",hoscode);
        }

        //调用方法实现分页查询
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, wrapper);
        //返回结果
        return Result.ok(hospitalSetPage);

    }

    //添加医院设置
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        //设置状态 1：可以使用 0：不可使用
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(UUID.randomUUID().toString());
        boolean save = hospitalSetService.save(hospitalSet);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //根据id获取医院设置
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        //手动抛出异常
          /* try {
               int i = 1 / 0;
         }catch (Exception e){
               throw new YyghException("失败",201);
          }*/
        HospitalSet hospitalSet = hospitalSetService.getById(id);

        return Result.ok(hospitalSet);
    }

    //修改医院设置
    @PostMapping("updateHospSet")
    public Result updateHospSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    //批量删除医院设置接口
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        boolean flag = hospitalSetService.removeByIds(idList);
        if(flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //医院设置锁定和解锁
    @PutMapping("lockHospSet/{id}/{status}")
    public Result lockHospSet(@PathVariable long id,
                              @PathVariable Integer status){
        //根据id查询设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }

    //发送签名的秘钥KEY
    @PutMapping("sendKey/{id}")
    public Result lockHospSet(@PathVariable long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //发送短信
        return Result.ok();
    }
}
