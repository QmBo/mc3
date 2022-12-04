package ru.qmbo.mc3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import ru.qmbo.mc3.model.Message;
import ru.qmbo.mc3.service.MessageSendService;

import java.util.Date;

/**
 * KafkaMessageController
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 02.12.2022
 */
@Controller
@Log4j2
public class KafkaMessageController {

    private final MessageSendService messageSendService;

    /**
     * Instantiates a new Kafka message controller.
     *
     * @param messageSendService the message send service
     */
    public KafkaMessageController(MessageSendService messageSendService) {
        this.messageSendService = messageSendService;
    }

    /**
     * Gets message.
     *
     * @param input the input
     */
    @KafkaListener(topics = {"cycle-message"})
    public void getMessage(ConsumerRecord<Integer, String> input) {
        try {
        Date date = new Date(System.currentTimeMillis());
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(input.value(), Message.class);
        this.messageSendService.send(message.setMc3Timestamp(date));
        } catch (JsonProcessingException e) {
            log.error("String to Message parse fail.");
        }
    }
}
