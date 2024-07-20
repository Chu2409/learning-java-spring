package com.edzo.postrgres_connection.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edzo.postrgres_connection.TestDataUtil;
import com.edzo.postrgres_connection.domain.Author;
import com.edzo.postrgres_connection.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
  private BookRepository underTest;

  @Autowired
  public BookRepositoryIntegrationTest(BookRepository underTest) {
    this.underTest = underTest;
  }

  @Test
  public void testThatBookCanBeCreatedAndRecalled() {
    Author author = TestDataUtil.createTestAuthorA();

    Book book = TestDataUtil.createTestBookA(author);
    underTest.save(book);

    Optional<Book> result = underTest.findById(book.getIsbn());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), book);
  }

  @Test
  public void testThatMultiplesCanBeCreatedAndRecalled() {
    Author author = TestDataUtil.createTestAuthorA();

    Book bookA = TestDataUtil.createTestBookA(author);
    underTest.save(bookA);
    Book bookB = TestDataUtil.createTestBookB(author);
    underTest.save(bookB);
    Book bookC = TestDataUtil.createTestBookC(author);
    underTest.save(bookC);

    Iterable<Book> result = underTest.findAll();

    assertEquals(List.of(bookA, bookB, bookC), result);
  }

  @Test
  public void testThatBookCanBeUpdated() {
    Author author = TestDataUtil.createTestAuthorA();

    Book book = TestDataUtil.createTestBookA(author);
    underTest.save(book);

    book.setTitle("UPDATED");
    underTest.save(book);

    Optional<Book> result = underTest.findById(book.getIsbn());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), book);
  }

  @Test
  public void testThatBookCanBeDeleted() {
    Author author = TestDataUtil.createTestAuthorA();

    Book book = TestDataUtil.createTestBookA(author);
    underTest.save(book);

    underTest.deleteById(book.getIsbn());

    Optional<Book> result = underTest.findById(book.getIsbn());

    assertTrue(result.isEmpty());
  }
}
