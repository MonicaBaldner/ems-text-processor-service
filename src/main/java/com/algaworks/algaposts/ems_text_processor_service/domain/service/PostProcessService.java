package com.algaworks.algaposts.ems_text_processor_service.domain.service;

import com.algaworks.algaposts.ems_text_processor_service.api.model.PostMessage;
import com.algaworks.algaposts.ems_text_processor_service.api.model.PostResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.algaworks.algaposts.ems_text_processor_service.infrastructure.rabbitmq.RabbitMQConfig.FANOUT_EXCHANGE_NAME_RESULT;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostProcessService {

        public static final BigDecimal VALOR_POR_PALAVRA = BigDecimal.valueOf(0.10);

        private final RabbitTemplate rabbitTemplate;

         public void monetizarPost(PostMessage postMessage) {

             log.info("Está em monetizarPost de PostProcessingService");

            //para testar dql
           /*  if ("forcar erro para teste de dql".equals(postMessage.getBody())) {
                 throw new RuntimeException("Simulando erro no serviço");
             }*/
            //fim para testar dql

             Integer totalPalavras = totalDePalavras(postMessage.getBody());

             BigDecimal valorDoPost = VALOR_POR_PALAVRA.multiply(BigDecimal.valueOf(totalPalavras));


             String exchange = FANOUT_EXCHANGE_NAME_RESULT;
             String routingKey = "";
             PostResult payload = PostResult.builder()
                     .postId(postMessage.getPostId())
                     .wordCount(totalPalavras)
                     .calculatedValue(valorDoPost)
                     .build();

             log.info(String.valueOf(payload));

             rabbitTemplate.convertAndSend(exchange, routingKey, payload);

        }

        private static Integer totalDePalavras(String body){
            if (body == null || body.trim().isEmpty()) {
                return 0;
            }

            String[] palavras = body.trim().split("\\s+");
            int totalPalavras = palavras.length;

            log.info("Total de Palavras {}  ", totalPalavras);

            return totalPalavras;
        }


}
