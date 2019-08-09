package com.firststringboot.common.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(ConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.warn("=============>ConfirmCallBackListener:生产者发出一条消息===============>"+s);
    }
}
