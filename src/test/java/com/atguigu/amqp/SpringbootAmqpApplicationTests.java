package com.atguigu.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 单播
     */

    @Test
    void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("liu","hui");
        map.put("zhang","lang");
        rabbitTemplate.convertAndSend("atguigu","atguigu.news",map);
    }

    @Test
    void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */

    @Test
    void broadcast(){
        Map<String,Object> map = new HashMap<>();
        map.put("liu","hui");
        map.put("zhang","lang");
        rabbitTemplate.convertAndSend("atguigu.fanout","atguigu.news",map);
    }

    /**
     * 正则
     */
    @Test
    void zhengze(){
        Map<String,Object> map = new HashMap<>();
        map.put("liu","hui");
        map.put("zhang","lang");
        rabbitTemplate.convertAndSend("atgu igu.topic","atguigu",map);
    }

    @Test
    void amqpAdminTest(){
        amqpAdmin.declareExchange(new DirectExchange("langlalngNewExchange"));
        amqpAdmin.declareQueue(new Queue("langlangNewQueue"));
        amqpAdmin.declareBinding(new Binding("langlangNewQueue",
                Binding.DestinationType.QUEUE,"langlalngNewExchange","langlangNewQueue",null));
    }
}
