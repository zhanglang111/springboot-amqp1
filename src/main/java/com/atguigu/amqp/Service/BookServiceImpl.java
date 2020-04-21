package com.atguigu.amqp.Service;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Override
    public void getService() {
        System.out.println("被触发");
        return;
    }
}
