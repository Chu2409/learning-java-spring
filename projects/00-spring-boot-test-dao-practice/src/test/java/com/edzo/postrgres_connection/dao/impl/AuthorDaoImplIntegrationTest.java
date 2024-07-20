package com.edzo.postrgres_connection.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

  private AuthorDaoImpl underTest;

  @Autowired
  public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
    this.underTest = underTest;
  }

  @Test
  public void testThatAuthorCanBeCreatedAndRecalled() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.create(author);

    Optional<Author> result = underTest.findOne(author.getId());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), author);
  }

  @Test
  public void testThatMultiplesCanBeCreatedAndRecalled() {
    Author authorA = TestDataUtil.createTestAuthorA();
    underTest.create(authorA);
    Author authorB = TestDataUtil.createTestAuthorB();
    underTest.create(authorB);
    Author authorC = TestDataUtil.createTestAuthorC();
    underTest.create(authorC);

    List<Author> result = underTest.findMany();

    assertEquals(result.size(), 3);
    assertTrue(result.containsAll(List.of(authorA, authorB, authorC)));
  }

  @Test
  public void testThatAuthorCanBeUpdated() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.create(author);

    author.setName("UPDATED");
    underTest.update(author.getId(), author);

    Optional<Author> result = underTest.findOne(author.getId());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), author);
  }

  @Test
  public void testThatAuthorCanBeDeleted() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.create(author);

    underTest.delete(author.getId());

    Optional<Author> result = underTest.findOne(author.getId());

    assertTrue(result.isEmpty());
  }

}
