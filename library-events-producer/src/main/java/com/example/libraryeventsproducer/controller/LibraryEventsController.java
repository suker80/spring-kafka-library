package com.example.libraryeventsproducer.controller;

import com.example.libraryeventsproducer.component.LibraryEventProducer;
import com.example.libraryeventsproducer.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LibraryEventsController {
    private final LibraryEventProducer producer;


    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> libraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {

        log.info("library event : {} ", libraryEvent);

//        producer.sendLibraryEvent(libraryEvent);
        producer.sendLibraryEventWithProducerRecord(libraryEvent);
        return ResponseEntity.ok(libraryEvent);
    }
}
