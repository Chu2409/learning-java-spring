package com.edzo.database.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.edzo.database.TestDataUtil;
import com.edzo.database.domains.dtos.BookDto;
import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.domains.entities.BookEntity;
import com.edzo.database.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BookService booksService;

  private ObjectMapper objectMapper;

  public BookControllerIntegrationTest() {
    this.objectMapper = new ObjectMapper();
  }

  @Test
  public void testThatCreateBookReturns201Created() throws Exception {
    BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
    String bookJson = objectMapper.writeValueAsString(bookDto);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJson))
        .andExpect(
            MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testThatUpdateBookReturns200Ok() throws Exception {
    BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
    String bookJson = objectMapper.writeValueAsString(bookDto);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJson))
        .andExpect(
            MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testThatCreateBookReturnsCreatedBook() throws Exception {
    BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
    String bookJson = objectMapper.writeValueAsString(bookDto);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookJson))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle()));
  }

  @Test
  public void testThatListBooksReturns200Ok() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.status().isOk());
  }

  // @Test
  // public void testThatListBooksReturnsListOfAuthors() throws Exception {
  // AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
  // BookEntity testBookA = TestDataUtil.createTestBookA(testAuthorA);
  // booksService.createUpdateBook(testBookA.getIsbn(), testBookA);

  // mockMvc.perform(
  // MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
  // .andExpect(
  // MockMvcResultMatchers.jsonPath("$[0].isbn").value("12321"))
  // .andExpect(
  // MockMvcResultMatchers.jsonPath("$[0].title").value("The Book"));
  // }

  @Test
  public void testThatListAuthorReturns200OkWhenAuthorExist() throws Exception {
    BookEntity testBookA = TestDataUtil.createTestBookA(null);
    booksService.createUpdateBook(testBookA.getIsbn(), testBookA);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/books/12321").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatListAuthorsReturns404WhenAuthorNotExist() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/books/12321").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatListAuthorsReturnsAuthorWhenAuthorExist() throws Exception {
    BookEntity testBookA = TestDataUtil.createTestBookA(null);
    booksService.createUpdateBook(testBookA.getIsbn(), testBookA);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/books/12321").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").value("12321"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value("The Book"));
  }
}
