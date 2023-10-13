package com.xuda.yygh.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuda.yygh.model.order.OrderInfo;
import com.xuda.yygh.vo.order.OrderCountQueryVo;
import com.xuda.yygh.vo.order.OrderQueryVo;

import java.util.Map;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-16 13:46
 */

public interface OrderService extends IService<OrderInfo> {
        //生成挂号订单
        Long saveOrder(String scheduleId, Long patientId);

        //根据订单id查询订单详情
        OrderInfo getOrder(String orderId);

        //订单列表（条件查询带分页）
        IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

        //取消预约
        Boolean cancelOrder(Long orderId);

        //就诊通知
        void patientTips();

        //预约统计
        Map<String,Object> getCountMap(OrderCountQueryVo orderCountQueryVo );


}
