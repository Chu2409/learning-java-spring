package com.edzo.postrgres_connection.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edzo.postrgres_connection.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
  Iterable<Author> ageLessThan(int age);

  @Query("SELECT a FROM Author a WHERE a.age > ?1")
  Iterable<Author> findAuthorsWithAgeGreatherThan(int age);

}
