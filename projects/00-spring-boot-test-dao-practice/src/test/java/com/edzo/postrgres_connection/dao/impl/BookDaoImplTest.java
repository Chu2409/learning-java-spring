package com.edzo.postrgres_connection.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.edzo.postrgres_connection.TestDataUtil;
import com.edzo.postrgres_connection.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private BookDaoImpl underTest;

  @Test
  public void testThatCreateBookGeneratesCorrectSql() {
    Book book = TestDataUtil.createTestBookA();

    underTest.create(book);

    verify(jdbcTemplate).update(
        eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
        eq("12321"), eq("The Book"), eq(1L));
  }

  @Test
  public void testThatFindOneBookGeneratesCorrectSql() {
    underTest.findOne("12321");
    verify(jdbcTemplate).query(
        eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
        ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
        eq("12321"));
  }

  @Test
  public void testThatFindManyGeneratesTheCorrectSql() {
    underTest.findMany();
    verify(jdbcTemplate).query(
        eq("SELECT isbn, title, author_id FROM books"),
        ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
  }

  @Test
  public void testThatUpdateGeneratesCorrectSql() {
    Book book = TestDataUtil.createTestBookA();
    underTest.update("12321", book);

    verify(jdbcTemplate).update(
        "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
        "12321", "The Book", 1L, "12321");
  }

  @Test
  public void testThatDeleteGeneratesCorrectSql() {
    underTest.delete("12321");

    verify(jdbcTemplate).update(
        "DELETE FROM books WHERE isbn = ?",
        "12321");
  }
}
