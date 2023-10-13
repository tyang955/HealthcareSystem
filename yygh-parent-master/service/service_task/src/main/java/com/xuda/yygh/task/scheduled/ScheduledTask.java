package com.xuda.yygh.task.scheduled;

import com.xuda.common.rabbit.constant.MqConst;
import com.xuda.common.rabbit.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-17 9:27
 */

@Component
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private RabbitService rabbitService;

    //每天8点执行方法，就医提醒
    //cron表达式，设置执行间隔
    //0 0 8 * * ?  //每隔八点进行发送，下面为30秒进行测试
    @Scheduled(cron = "0/30 * * * * ?")
    public void taskPatient() {
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK,MqConst.ROUTING_TASK_8,"");
    }
}
