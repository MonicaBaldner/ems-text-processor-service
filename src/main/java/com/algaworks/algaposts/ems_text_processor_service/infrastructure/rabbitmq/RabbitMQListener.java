package com.algaworks.algaposts.ems_text_processor_service.infrastructure.rabbitmq;

import com.algaworks.algaposts.ems_text_processor_service.api.model.PostMessage;
import com.algaworks.algaposts.ems_text_processor_service.domain.service.PostProcessService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import java.time.Duration;

import static com.algaworks.algaposts.ems_text_processor_service.infrastructure.rabbitmq.RabbitMQConfig.QUEUE_POST;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    private final PostProcessService postProcessService;

    @SneakyThrows
    @RabbitListener(queues = QUEUE_POST)
    public void handle(@Payload PostMessage postMessage){

        log.info("Está no listener de text-processor");
        String id = postMessage.getPostId();
       String body = postMessage.getBody();
       log.info("Message Received: Id {} Body {}", id, body);

      //  Thread.sleep(Duration.ofSeconds(15));

       postProcessService.monetizarPost(postMessage);


    }
}
