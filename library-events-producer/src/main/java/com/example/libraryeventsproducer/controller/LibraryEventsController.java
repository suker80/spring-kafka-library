package com.example.libraryeventsproducer.controller;

import com.example.libraryeventsproducer.domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryEventsController {


    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> libraryEvent(@RequestBody LibraryEvent libraryEvent) {

        log.info("library event : {} ", libraryEvent);

        return ResponseEntity.ok(libraryEvent);
    }
}
