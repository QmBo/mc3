package ru.qmbo.mc3.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.qmbo.mc3.model.Message;

import static java.lang.String.format;

/**
 * MessageSendService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 02.12.2022
 */
@Service
@Log4j2
public class MessageSendService {
    private final RestTemplate client;
    private final String url;

    /**
     * Instantiates a new Message send service.
     *
     * @param client the client
     * @param url    the url
     */
    public MessageSendService(RestTemplate client, @Value("${mc1.url}") String url) {
        this.client = client;
        this.url = url;
    }

    /**
     * Send message.
     *
     * @param message the message
     */
    public void send(Message message) {
        log.debug("New message: {}", message);
        this.client.postForEntity(
                format("%s/messages", url),
                message,
                Message.class
        );
    }
}
