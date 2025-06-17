package com.algaworks.algaposts.ems_text_processor_service.infrastructure.rabbitmq;

import com.algaworks.algaposts.ems_text_processor_service.api.model.PostMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import java.time.Duration;

import static com.algaworks.algaposts.ems_text_processor_service.infrastructure.rabbitmq.RabbitMQConfig.QUEUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    @SneakyThrows
    @RabbitListener(queues = QUEUE)
    public void handle(@Payload PostMessage postMessage){
       String id = postMessage.getId();
       String body = postMessage.getBody();
       log.info("Message Received: Id{} Body{}", id, body);

        Thread.sleep(Duration.ofSeconds(15));
    }
}
