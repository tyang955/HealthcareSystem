package com.xuda.yygh.cmn.controller;

import com.xuda.yygh.common.result.Result;
import com.xuda.yygh.model.cmn.Dict;
import com.xuda.yygh.cmn.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-05 21:02
 */
@Api(value = "数据字典接口")
@RestController
//@CrossOrigin //允许跨域请求
@RequestMapping(value = "/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    //根据id查询下面的子分类
    @ApiOperation(value = "根据数据id获取子节点")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
      List<Dict> list =  dictService.findChildData(id);
      return Result.ok(list);
    }

    //导出数据字典接口
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportDictData(response);
    }

    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importData(file);
        return Result.ok();
    }

    /**
     * 根据dictCode和value查询数据
     * @param dictCode
     * @param value
     * @return
     */
    @GetMapping("getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value) {
        String dictName = dictService.getDictName(dictCode,value); //返回name的值
        return dictName;
    }
    @ApiOperation(value = "根据名称获取")
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value) {
        String dictName = dictService.getDictName("",value); //返回name的值
        return dictName;
    }

    //根据dictCode获取下级节点
    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping("findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }
}
