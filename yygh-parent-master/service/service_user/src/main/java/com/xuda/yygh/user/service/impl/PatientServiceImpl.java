package com.xuda.yygh.user.service.impl;


//import com.xuda.yygh.cmn.client.DictFeignClient;
import com.xuda.yygh.cmn.client.DictFeignClient;
import com.xuda.yygh.enums.DictEnum;
import com.xuda.yygh.model.user.Patient;
import com.xuda.yygh.model.user.UserInfo;
import com.xuda.yygh.user.mapper.PatientMapper;
import com.xuda.yygh.user.mapper.UserInfoMapper;
import com.xuda.yygh.user.service.PatientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientServiceImpl extends
        ServiceImpl<PatientMapper, Patient> implements PatientService {

    @Autowired
    private DictFeignClient dictFeignClient;

    //根据登录用户id获取就诊列表
    @Override
    public List<Patient> findAllUserId(Long userId) {
        //据登录用户id查询所有就诊人信息列表
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Patient> patientList = baseMapper.selectList(wrapper);
        //通过远程调用得到部分编码对应的具体内容，查询数据字典表中的内容  例如 编号2000对应的是身份证方式
        patientList.stream().forEach( item -> {
            this.packPatient(item);  //将param中的一些属性封装进patient中
        });
        return patientList;
    }



    //根据就诊人id获取就诊人信息
    @Override
    public Patient getPatientId(Long id) {
        Patient patient = baseMapper.selectById(id);
        this.packPatient(patient);
        return patient;
    }

    //将param中的一些属性封装进patient中
    private Patient packPatient(Patient patient) {
        //根据证件类型，获取证件类型名称
        String certificatesTypeString = dictFeignClient.getName
                (DictEnum.CERTIFICATES_TYPE.getDictCode(), patient.getCertificatesType());
        //联系人证件类型
        String contactsCertificatesTypeString = dictFeignClient.getName
                (DictEnum.CERTIFICATES_TYPE.getDictCode(),patient.getContactsCertificatesType());

        //省
        String provinceString = dictFeignClient.getName(patient.getProvinceCode());
        //市
        String cityString = dictFeignClient.getName(patient.getCityCode());
        //区
        String districtString = dictFeignClient.getName(patient.getDistrictCode());
        patient.getParam().put("certificatesTypeString", certificatesTypeString);
        patient.getParam().put("contactsCertificatesTypeString", contactsCertificatesTypeString);
        patient.getParam().put("provinceString", provinceString);
        patient.getParam().put("cityString", cityString);
        patient.getParam().put("districtString", districtString);
        patient.getParam().put("fullAddress", provinceString + cityString + districtString + patient.getAddress());
        return patient;
    }
}
