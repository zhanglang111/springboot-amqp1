package com.atguigu.amqp.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    @RabbitListener(queues = "atguigu.news")
    public void getService();
}
