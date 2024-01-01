package com.example.libraryeventsproducer.component;


import com.example.libraryeventsproducer.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryEventProducer {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.topic}")
    public String topic;

    public CompletableFuture<SendResult<Integer, String>> sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {

        var key = libraryEvent.libraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);

        CompletableFuture<SendResult<Integer, String>> completableFuture = kafkaTemplate.send(topic, key, value);

        return completableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                heandleFailure(key, value, throwable);
            } else {
                heandleSuccess(key, value, sendResult);
            }
        });
    }

    private void heandleSuccess(Integer key, String value, SendResult<Integer, String> sendResult) {

        log.info("Message sent successfully for the key : {} and value : {} partition is : {} ", key, value, sendResult, sendResult.getRecordMetadata().partition());
    }

    private void heandleFailure(Integer key, String value, Throwable throwable) {
        log.error("Error sending the message and exception is {}", throwable.getMessage());
    }

}
