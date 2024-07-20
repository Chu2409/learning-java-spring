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
import com.edzo.postrgres_connection.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private AuthorDaoImpl underTest;

  @Test
  public void testThatCreateAuthorGeneratesCorrectSql() {
    Author author = TestDataUtil.createTestAuthorA();

    underTest.create(author);

    verify(jdbcTemplate).update(
        eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
        eq(1L),
        eq("John Doe"),
        eq(20));
  }

  @Test
  public void testThatFindOneGeneratesTheCorrectSql() {
    underTest.findOne(1L);
    verify(jdbcTemplate).query(
        eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
        ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
        eq(1L));
  }

  @Test
  public void testThatFindManyGeneratesTheCorrectSql() {
    underTest.findMany();
    verify(jdbcTemplate).query(
        eq("SELECT id, name, age FROM authors"),
        ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
  }

  @Test
  public void testThatUpdateGeneratesCorrectSql() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.update(3L, author);

    verify(jdbcTemplate).update(
        "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
        1L,
        "John Doe",
        20,
        3L);
  }

  @Test
  public void testThatDeleteGeneratesCorrectSql() {
    underTest.delete(3L);

    verify(jdbcTemplate).update(
        "DELETE FROM authors WHERE id = ?",
        3L);
  }
}
