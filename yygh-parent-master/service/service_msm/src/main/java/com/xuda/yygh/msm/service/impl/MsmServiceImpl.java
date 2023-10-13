package com.xuda.yygh.msm.service.impl;

import com.xuda.yygh.msm.service.MsmService;
import com.xuda.yygh.msm.utils.ConstantProperties;
import com.xuda.yygh.vo.msm.MsmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-13 21:12
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    JavaMailSender javaMailSender; //短信发送

    @Override
    public boolean send(String phone, String code) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        //整合阿里云短信服务
        //设置相关参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantProperties.REGION_Id,
                        ConstantProperties.ACCESS_KEY_ID,
                        ConstantProperties.SECRECT);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_180051135");
        //验证码  使用json格式   {"code":"123456"}
        Map<String,Object> param = new HashMap();
        param.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }



    //mq发送短信封装
    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())) {
            boolean isSend = this.send(msmVo.getPhone(), msmVo.getParam());
            return isSend;
        }
        return false;
    }
//验证码qq邮箱发送
    @Async //加上异步不会去等待，
    @Override
    public void sendEmail(String email, String code) {
        SimpleMailMessage mailMessage =new SimpleMailMessage();
        mailMessage.setSubject("尚医通登录验证码");//主题
        mailMessage.setText("尊敬的用户"+email+"您注册的校验码为:"+ code + "，有效期为5分钟"); //内容
//           int code = number;//生成的随机数放入code中
        mailMessage.setTo(email);//发给谁
        mailMessage.setFrom("1375595011@qq.com");//自己的邮箱
        javaMailSender.send(mailMessage);
    }

    //生成验证码
    @Override
    public String getCode() {
        int random = (int) (Math.random() * 1000000);
        String code = String.format("%06d", random);
        return code;
    }

    private boolean send(String phone, Map<String,Object> param) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        //整合阿里云短信服务
        //设置相关参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantProperties.REGION_Id,
                        ConstantProperties.ACCESS_KEY_ID,
                        ConstantProperties.SECRECT);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_180051135");

        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
