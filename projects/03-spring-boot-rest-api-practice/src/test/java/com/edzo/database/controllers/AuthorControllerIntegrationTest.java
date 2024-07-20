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
import com.edzo.database.domains.dtos.AuthorDto;
import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AuthorService authorService;

  private ObjectMapper objectMapper;

  public AuthorControllerIntegrationTest() {
    this.objectMapper = new ObjectMapper();
  }

  @Test
  public void testThatCreateAuthorSuccessfullyReturns201Created() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    testAuthorA.setId(null);
    String authorJson = objectMapper.writeValueAsString(testAuthorA);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/authors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(authorJson))
        .andExpect(
            MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    testAuthorA.setId(null);
    String authorJson = objectMapper.writeValueAsString(testAuthorA);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/authors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(authorJson))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value(testAuthorA.getName()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(testAuthorA.getAge()));
  }

  @Test
  public void testThatListAuthorsReturns200Ok() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/authors").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    authorService.save(testAuthorA);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/authors").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$[0].age").value(20));
  }

  @Test
  public void testThatListAuthorsReturns200OkWhenAuthorExist() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    authorService.save(testAuthorA);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/authors/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatListAuthorsReturns404WhenAuthorNotExist() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/authors/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatListAuthorsReturnsAuthorWhenAuthorExist() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    authorService.save(testAuthorA);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/authors/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(20));
  }

  @Test
  public void testThatFullUpdateAuthorReturns404WhenAuthorNoExists() throws Exception {
    AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
    String authorJson = objectMapper.writeValueAsString(authorDto);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/authors/99")
            .contentType(MediaType.APPLICATION_JSON)
            .content(authorJson))
        .andExpect(
            MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatFullUpdateAuthorReturns200WhenAuthorExists() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    AuthorEntity savedAuthor = authorService.save(testAuthorA);

    AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
    String authorJson = objectMapper.writeValueAsString(authorDto);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(authorJson))
        .andExpect(
            MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    AuthorEntity savedAuthor = authorService.save(testAuthorA);

    AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
    String authorJson = objectMapper.writeValueAsString(authorDto);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(authorJson))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("Alice Smith"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(30));
  }

}
