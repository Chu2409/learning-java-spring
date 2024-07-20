package com.edzo.database;

import com.edzo.database.domains.dtos.AuthorDto;
import com.edzo.database.domains.dtos.BookDto;
import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.domains.entities.BookEntity;

public final class TestDataUtil {
  private TestDataUtil() {
  }

  public static AuthorEntity createTestAuthorA() {
    return AuthorEntity.builder().id(1L).name("John Doe").age(20).build();
  }

  public static AuthorEntity createTestAuthorB() {
    return AuthorEntity.builder().id(2L).name("Alice Smith").age(30).build();
  }

  public static AuthorEntity createTestAuthorC() {
    return AuthorEntity.builder().id(3L).name("Bob Brown").age(40).build();
  }

  public static AuthorDto createTestAuthorDtoA() {
    return AuthorDto.builder().id(1L).name("John Doe").age(20).build();
  }

  public static BookEntity createTestBookA(final AuthorEntity author) {
    return BookEntity.builder().isbn("12321").title("The Book").author(author).build();
  }

  public static BookEntity createTestBookB(final AuthorEntity author) {
    return BookEntity.builder().isbn("12322").title("The Book 2").author(author).build();
  }

  public static BookEntity createTestBookC(final AuthorEntity author) {
    return BookEntity.builder().isbn("12323").title("The Book 3").author(author).build();
  }

  public static BookDto createTestBookDtoA(final AuthorDto author) {
    return BookDto.builder().isbn("12321").title("The Book").author(author).build();
  }
}
