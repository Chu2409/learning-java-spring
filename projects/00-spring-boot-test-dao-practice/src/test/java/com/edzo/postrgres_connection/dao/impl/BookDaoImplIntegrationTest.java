package com.edzo.postrgres_connection.dao.impl;

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
import com.edzo.postrgres_connection.dao.AuthorDao;
import com.edzo.postrgres_connection.domain.Author;
import com.edzo.postrgres_connection.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {
  private BookDaoImpl underTest;
  private AuthorDao authorDao;

  @Autowired
  public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
    this.underTest = underTest;
    this.authorDao = authorDao;
  }

  @Test
  public void testThatBookCanBeCreatedAndRecalled() {
    Author author = TestDataUtil.createTestAuthorA();
    authorDao.create(author);

    Book book = TestDataUtil.createTestBookA();
    underTest.create(book);

    Optional<Book> result = underTest.findOne("12321");

    assertNotNull(result.isPresent());
    assertEquals(result.get(), book);
  }

  @Test
  public void testThatMultiplesCanBeCreatedAndRecalled() {
    Author author = TestDataUtil.createTestAuthorA();
    authorDao.create(author);

    Book bookA = TestDataUtil.createTestBookA();
    underTest.create(bookA);
    Book bookB = TestDataUtil.createTestBookB();
    underTest.create(bookB);
    Book bookC = TestDataUtil.createTestBookC();
    underTest.create(bookC);

    List<Book> result = underTest.findMany();

    assertEquals(result.size(), 3);
    assertTrue(result.containsAll(List.of(bookA, bookB, bookC)));
  }

  @Test
  public void testThatBookCanBeUpdated() {
    Author author = TestDataUtil.createTestAuthorA();
    authorDao.create(author);

    Book book = TestDataUtil.createTestBookA();
    underTest.create(book);

    book.setTitle("UPDATED");
    underTest.update("12321", book);

    Optional<Book> result = underTest.findOne("12321");

    assertNotNull(result.isPresent());
    assertEquals(result.get(), book);
  }

  @Test
  public void testThatBookCanBeDeleted() {
    Author author = TestDataUtil.createTestAuthorA();
    authorDao.create(author);

    Book book = TestDataUtil.createTestBookA();
    underTest.create(book);

    underTest.delete("12321");

    Optional<Book> result = underTest.findOne("12321");

    assertTrue(result.isEmpty());
  }
}
