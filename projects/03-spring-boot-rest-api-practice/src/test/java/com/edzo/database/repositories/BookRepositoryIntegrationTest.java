package com.edzo.database.repositories;

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

import com.edzo.database.TestDataUtil;
import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.domains.entities.BookEntity;

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
    AuthorEntity author = TestDataUtil.createTestAuthorA();

    BookEntity book = TestDataUtil.createTestBookA(author);
    underTest.save(book);

    Optional<BookEntity> result = underTest.findById(book.getIsbn());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), book);
  }

  @Test
  public void testThatMultiplesCanBeCreatedAndRecalled() {
    AuthorEntity author = TestDataUtil.createTestAuthorA();

    BookEntity bookA = TestDataUtil.createTestBookA(author);
    underTest.save(bookA);
    BookEntity bookB = TestDataUtil.createTestBookB(author);
    underTest.save(bookB);
    BookEntity bookC = TestDataUtil.createTestBookC(author);
    underTest.save(bookC);

    Iterable<BookEntity> result = underTest.findAll();

    assertEquals(List.of(bookA, bookB, bookC), result);
  }

  @Test
  public void testThatBookCanBeUpdated() {
    AuthorEntity author = TestDataUtil.createTestAuthorA();

    BookEntity book = TestDataUtil.createTestBookA(author);
    underTest.save(book);

    book.setTitle("UPDATED");
    underTest.save(book);

    Optional<BookEntity> result = underTest.findById(book.getIsbn());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), book);
  }

  @Test
  public void testThatBookCanBeDeleted() {
    AuthorEntity author = TestDataUtil.createTestAuthorA();

    BookEntity book = TestDataUtil.createTestBookA(author);
    underTest.save(book);

    underTest.deleteById(book.getIsbn());

    Optional<BookEntity> result = underTest.findById(book.getIsbn());

    assertTrue(result.isEmpty());
  }
}
