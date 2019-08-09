package com.firststringboot.common.config;


import com.firststringboot.common.rabbit.ConfirmCallBack;
import com.firststringboot.common.rabbit.ReturnCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigurer {

    private final Logger logger = LoggerFactory.getLogger(RabbitMqConfigurer.class);

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String v_host;


    public static final String EXCHANGE_A = "my-mq-exchange_A";

    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";

    public static final String exchange_A_Key = "my-mq-exchange_A_QUEUE_A_Key";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setPort(port);
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(v_host);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("directExchange");
        rabbitTemplate.setConfirmCallback(new ConfirmCallBack());
        rabbitTemplate.setReturnCallback(new ReturnCallBack());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }


    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_A, false, true);
    }

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, false,false,true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(directExchange()).with(exchange_A_Key);
    }

}
