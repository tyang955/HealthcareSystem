package com.xuda.yygh.controller;

import com.xuda.yygh.common.result.Result;
import com.xuda.yygh.model.hosp.Hospital;
import com.xuda.yygh.service.HospitalService;
import com.xuda.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-10 9:29
 */
@Api(tags = "医院管理接口")
@RestController
@RequestMapping("/admin/hosp/hospital")
//@CrossOrigin //跨域请求
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    /**
     * 医院列表条件查询分页
     * @param page
     * @param limit
     * @param hospitalQueryVo 对象传参数
     * @return
     */
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        List<Hospital> content = pageModel.getContent();
        long totalElements = pageModel.getTotalElements();
        return Result.ok(pageModel);
    }

    //更新医院上线状态
    @ApiOperation(value = "更新医院上线状态")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable String id,@PathVariable Integer status) {
        hospitalService.updateStatus(id,status);
        return Result.ok();
    }

    //医院详情信息
    @ApiOperation(value = "医院详情信息")
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(@PathVariable String id) {
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}

