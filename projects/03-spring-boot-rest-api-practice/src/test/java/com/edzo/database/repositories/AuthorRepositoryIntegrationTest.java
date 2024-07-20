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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

  @Autowired
  private AuthorRepository underTest;

  @Test
  public void testThatAuthorCanBeCreatedAndRecalled() {
    AuthorEntity author = TestDataUtil.createTestAuthorA();
    underTest.save(author);

    Optional<AuthorEntity> result = underTest.findById(author.getId());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), author);
  }

  @Test
  public void testThatMultiplesCanBeCreatedAndRecalled() {
    AuthorEntity authorA = TestDataUtil.createTestAuthorA();
    underTest.save(authorA);
    AuthorEntity authorB = TestDataUtil.createTestAuthorB();
    underTest.save(authorB);
    AuthorEntity authorC = TestDataUtil.createTestAuthorC();
    underTest.save(authorC);

    Iterable<AuthorEntity> result = underTest.findAll();

    assertEquals(List.of(authorA, authorB, authorC), result);
  }

  @Test
  public void testThatAuthorCanBeUpdated() {
    AuthorEntity author = TestDataUtil.createTestAuthorA();
    underTest.save(author);

    author.setName("UPDATED");
    underTest.save(author);

    Optional<AuthorEntity> result = underTest.findById(author.getId());

    assertNotNull(result.isPresent());
    assertEquals(result.get(), author);
  }

  @Test
  public void testThatAuthorCanBeDeleted() {
    AuthorEntity author = TestDataUtil.createTestAuthorA();
    underTest.save(author);

    underTest.deleteById(author.getId());

    Optional<AuthorEntity> result = underTest.findById(author.getId());

    assertTrue(result.isEmpty());
  }

  @Test
  public void testThatGetAuthorsWithAgeLessThan() {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    underTest.save(testAuthorA);

    AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
    underTest.save(testAuthorB);

    AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();
    underTest.save(testAuthorC);

    Iterable<AuthorEntity> result = underTest.ageLessThan(35);
    assertEquals(List.of(testAuthorA, testAuthorB), result);
  }

  @Test
  public void testThatGetAuthorsWithAgeGreaterThan() {
    AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
    underTest.save(testAuthorA);

    AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
    underTest.save(testAuthorB);

    AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();
    underTest.save(testAuthorC);

    Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreatherThan(35);

    assertEquals(List.of(testAuthorC), result);
  }

}
