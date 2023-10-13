package com.xuda.yygh.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuda.yygh.common.exception.YyghException;
import com.xuda.yygh.common.result.ResultCodeEnum;
import com.xuda.yygh.mapper.HospitalSetMapper;
import com.xuda.yygh.model.hosp.HospitalSet;
//import com.xuda.yygh.service.HospitalSetService;
import com.xuda.yygh.service.HospitalSetService;
import com.xuda.yygh.vo.order.SignInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 17:05
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    //根据传递过来的医院编码，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(queryWrapper);

        return hospitalSet.getSignKey();
    }

    //获取医院签名信息
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new YyghException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }
}
