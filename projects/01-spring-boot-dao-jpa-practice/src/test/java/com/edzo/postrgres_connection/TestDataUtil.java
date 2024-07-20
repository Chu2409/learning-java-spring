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

  public static Book createTestBookA(final Author author) {
    return Book.builder().isbn("12321").title("The Book").author(author).build();
  }

  public static Book createTestBookB(final Author author) {
    return Book.builder().isbn("12322").title("The Book 2").author(author).build();
  }

  public static Book createTestBookC(final Author author) {
    return Book.builder().isbn("12323").title("The Book 3").author(author).build();
  }
}
