package com.algaworks.algaposts.ems_text_processor_service.infrastructure.rabbitmq;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQInitializer {

    private final RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void init() {
        rabbitAdmin.initialize();
    }


}
