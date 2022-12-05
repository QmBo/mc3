package ru.qmbo.mc3.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.qmbo.mc3.config.KafkaProducerService;
import ru.qmbo.mc3.model.Message;
import ru.qmbo.mc3.service.MessageSendService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class KafkaMessageControllerTest {
    @Autowired
    private KafkaProducerService service;
    @Autowired
    private KafkaMessageController consumer;
    @MockBean
    private MessageSendService sendService;
    @Captor
    private ArgumentCaptor<Message> captor;
    @Test
    public void whenMessageThenSendMessage() throws IOException, InterruptedException {
        this.service.send(new Message());
        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        verify(sendService).send(captor.capture());
        assertThat(messageConsumed).isTrue();
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getMc3Timestamp()).isNotNull();
    }
}