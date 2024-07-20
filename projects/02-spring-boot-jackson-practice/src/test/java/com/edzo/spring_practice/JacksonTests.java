package com.edzo.spring_practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.edzo.spring_practice.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTests {
  @Test
  public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Book book = Book.builder()
        .isbn("978-3-16-148410-0")
        .title("The Art of War")
        .author("Sun Tzu")
        .yearPublished("5th Century BC")
        .build();

    String result = objectMapper.writeValueAsString(book);
    assertEquals(result,
        "{\"isbn\":\"978-3-16-148410-0\",\"title\":\"The Art of War\",\"author\":\"Sun Tzu\",\"year\":\"5th Century BC\"}");
  }

  @Test
  public void testThatObjectMapperCanCreateJavaObjectFromJsonObject()
      throws JsonMappingException, JsonProcessingException {
    Book book = Book.builder()
        .isbn("978-3-16-148410-0")
        .title("The Art of War")
        .author("Sun Tzu")
        .yearPublished("5th Century BC")
        .build();

    String json = "{\"isbn\":\"978-3-16-148410-0\",\"title\":\"The Art of War\",\"author\":\"Sun Tzu\",\"year\":\"5th Century BC\"}";

    final ObjectMapper objectMapper = new ObjectMapper();

    Book result = objectMapper.readValue(json, Book.class);

    assertEquals(result, book);
  }
}
