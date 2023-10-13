package com.xuda.yygh.user.service;

import com.xuda.yygh.model.user.Patient;
import com.xuda.yygh.model.user.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientService extends IService<Patient> {
    //获取就诊人列表
    List<Patient> findAllUserId(Long userId);

    //根据id获取就诊人信息
    Patient getPatientId(Long id);
}
