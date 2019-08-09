package com.firststringboot.common.listener;

import com.firststringboot.common.config.RabbitMqConfigurer;
import com.firststringboot.repository.domain.User;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMqConfigurer.QUEUE_A)
public class TwoConsume {

    private final Logger logger = LoggerFactory.getLogger(TwoConsume.class);

    @RabbitHandler
    public void process(String s) {
        logger.info("接收处理队列B当中的消息： " + s);
    }

    @RabbitHandler
    public void process(User o, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws Exception{
        channel.basicQos(1);
        try {
            Thread.sleep(5000);
        }catch (Exception e){

        }
        logger.info("接收处理队列B当中的消息： " +  o.getNickName() );
        channel.basicAck(deliveryTag,false);
    }
}
