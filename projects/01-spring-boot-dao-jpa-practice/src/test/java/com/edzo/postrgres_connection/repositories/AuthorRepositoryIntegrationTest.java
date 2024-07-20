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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

  @Autowired
  private AuthorRepository underTest;

  @Test
  public void testThatAuthorCanBeCreatedAndRecalled() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.save(author);

    Optional<Author> result = underTest.findById(author.getId());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), author);
  }

  @Test
  public void testThatMultiplesCanBeCreatedAndRecalled() {
    Author authorA = TestDataUtil.createTestAuthorA();
    underTest.save(authorA);
    Author authorB = TestDataUtil.createTestAuthorB();
    underTest.save(authorB);
    Author authorC = TestDataUtil.createTestAuthorC();
    underTest.save(authorC);

    Iterable<Author> result = underTest.findAll();

    assertEquals(List.of(authorA, authorB, authorC), result);
  }

  @Test
  public void testThatAuthorCanBeUpdated() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.save(author);

    author.setName("UPDATED");
    underTest.save(author);

    Optional<Author> result = underTest.findById(author.getId());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), author);
  }

  @Test
  public void testThatAuthorCanBeDeleted() {
    Author author = TestDataUtil.createTestAuthorA();
    underTest.save(author);

    underTest.deleteById(author.getId());

    Optional<Author> result = underTest.findById(author.getId());

    assertTrue(result.isEmpty());
  }

  @Test
  public void testThatGetAuthorsWithAgeLessThan() {
    Author testAuthorA = TestDataUtil.createTestAuthorA();
    underTest.save(testAuthorA);

    Author testAuthorB = TestDataUtil.createTestAuthorB();
    underTest.save(testAuthorB);

    Author testAuthorC = TestDataUtil.createTestAuthorC();
    underTest.save(testAuthorC);

    Iterable<Author> result = underTest.ageLessThan(35);
    assertEquals(List.of(testAuthorA, testAuthorB), result);
  }

  @Test
  public void testThatGetAuthorsWithAgeGreaterThan() {
    Author testAuthorA = TestDataUtil.createTestAuthorA();
    underTest.save(testAuthorA);

    Author testAuthorB = TestDataUtil.createTestAuthorB();
    underTest.save(testAuthorB);

    Author testAuthorC = TestDataUtil.createTestAuthorC();
    underTest.save(testAuthorC);

    Iterable<Author> result = underTest.findAuthorsWithAgeGreatherThan(35);

    assertEquals(List.of(testAuthorC), result);
  }

}
