package com.emlakburada.emlakburada.queue;

import com.emlakburada.emlakburada.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements QueueService {
    private final AmqpTemplate rabbitTemplate;
    private final RabbitMqConfig config;

    @Autowired
    public RabbitMqService(AmqpTemplate rabbitTemplate, RabbitMqConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.config = config;
    }

    @Override
    public void sendMessage(String email) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), email);
    }
}