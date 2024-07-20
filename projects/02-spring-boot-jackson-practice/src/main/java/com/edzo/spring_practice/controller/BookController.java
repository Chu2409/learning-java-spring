package com.edzo.spring_practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edzo.spring_practice.domain.Book;

import lombok.extern.java.Log;

@RestController
@Log
public class BookController {
  @GetMapping("/books")
  public Book retrieveBook() {
    return Book.builder()
        .isbn("978-3-16-148410-0")
        .title("The Art of War")
        .author("Sun Tzu")
        .yearPublished("5th Century BC")
        .build();
  }

  @PostMapping("/books")
  public Book createBook(@RequestBody final Book book) {
    log.info("Book: " + book);
    return book;
  }
}
