package ru.qmbo.mc3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.qmbo.mc3.model.Message;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

/**
 * KafkaProducerService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.12.2022
 */
@Service
public class KafkaProducerService {
    private final KafkaTemplate<Integer, String> template;

    /**
     * Instantiates a new Kafka producer service.
     *
     * @param template the template
     */
    public KafkaProducerService(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    /**
     * Send message.
     *
     * @param message the message
     * @throws IOException the io exception
     */
    public void send(Message message) throws IOException {
        message.setMc2Timestamp(new Date(System.currentTimeMillis()));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringEmp = new StringWriter();
        objectMapper.writeValue(stringEmp, message);
        this.template.send("cycle-message", stringEmp.toString());
    }

}
