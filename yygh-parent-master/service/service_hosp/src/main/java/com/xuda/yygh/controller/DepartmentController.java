package com.xuda.yygh.controller;

import com.xuda.yygh.common.result.Result;
import com.xuda.yygh.service.DepartmentService;
import com.xuda.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-10 20:21
 */
@RestController
@RequestMapping("/admin/hosp/department")
//@CrossOrigin //跨域请求
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}
