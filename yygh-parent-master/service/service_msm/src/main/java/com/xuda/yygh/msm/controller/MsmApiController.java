package com.xuda.yygh.msm.controller;

import com.alibaba.excel.util.StringUtils;
import com.xuda.yygh.common.result.Result;
import com.xuda.yygh.msm.service.MsmService;
import com.xuda.yygh.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-13 21:11
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = RandomUtil.getFourBitRandom();
        //调用service方法，通过整合短信服务进行发送
        boolean isSend = msmService.send(phone,code);
        //生成验证码放到redis里面，设置有效时间
        if(isSend) {
            redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }
    }

    //发送qq邮箱验证码
    @GetMapping("sendEmail/{email}")
    public Result sendEmailCode(@PathVariable String email) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = msmService.getCode();
        //调用service方法，通过整合短信服务进行发送
          msmService.sendEmail(email,code);
        //生成验证码放到redis里面，设置有效时间
        redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
        return Result.ok();
    }
}


  /*  @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码 如果获取到返回ok
        // key 手机号 ，value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = RandomUtil.getSixBitRandom();
        redisTemplate.opsForValue().set(phone,code);
        return Result.ok();

        *//**
         *
         //调用service方法，通过整合短信服务进行发送
         boolean isSend = msmService.send(phone,code);
         //生成验证码放到redis里面，设置有效时间
         if(isSend) {
         redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
         return Result.ok();
         } else {
         return Result.fail().message("发送短信失败");
         }

         *//*
    }
}
*/