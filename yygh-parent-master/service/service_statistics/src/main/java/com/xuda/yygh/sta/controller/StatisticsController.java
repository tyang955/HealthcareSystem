package com.xuda.yygh.sta.controller;

import com.xuda.yygh.common.result.Result;
import com.xuda.yygh.order.client.OrderFeignClient;
import com.xuda.yygh.vo.order.OrderCountQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：程序员徐大大
 * @description：远程调用预约统计
 * @date ：2022-04-17 9:48
 */
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private OrderFeignClient orderFeignClient;

    //获取预约统计数据
    @GetMapping("getCountMap")
    public Result getCountMap(OrderCountQueryVo orderCountQueryVo) {
        Map<String, Object> countMap = orderFeignClient.getCountMap(orderCountQueryVo);
        return Result.ok(countMap);
    }
}
