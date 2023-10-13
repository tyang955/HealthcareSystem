package com.xuda.yygh.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuda.yygh.model.order.OrderInfo;
import com.xuda.yygh.vo.order.OrderCountQueryVo;
import com.xuda.yygh.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderInfo> {

    //查询预约统计数据的方法
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}