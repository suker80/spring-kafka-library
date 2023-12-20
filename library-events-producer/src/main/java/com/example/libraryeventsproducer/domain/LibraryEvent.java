package com.example.libraryeventsproducer.domain;



public record LibraryEvent(
        Integer libraryEventId,
        LibraryEventType libraryEventType,
        Book book

) {
}
