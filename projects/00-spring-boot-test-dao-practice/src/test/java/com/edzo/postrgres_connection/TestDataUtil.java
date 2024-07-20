package com.edzo.postrgres_connection;

import com.edzo.postrgres_connection.domain.Author;
import com.edzo.postrgres_connection.domain.Book;

public final class TestDataUtil {
  private TestDataUtil() {
  }

  public static Author createTestAuthorA() {
    return Author.builder().id(1L).name("John Doe").age(20).build();
  }

  public static Author createTestAuthorB() {
    return Author.builder().id(2L).name("Alice Smith").age(30).build();
  }

  public static Author createTestAuthorC() {
    return Author.builder().id(3L).name("Bob Brown").age(40).build();
  }

  public static Book createTestBookA() {
    return Book.builder().isbn("12321").title("The Book").authorId(1L).build();
  }

  public static Book createTestBookB() {
    return Book.builder().isbn("12322").title("The Book 2").authorId(1L).build();
  }

  public static Book createTestBookC() {
    return Book.builder().isbn("12323").title("The Book 3").authorId(1L).build();
  }
}
