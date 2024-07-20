package com.edzo.database.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edzo.database.domains.entities.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
  Iterable<AuthorEntity> ageLessThan(int age);

  @Query("SELECT a FROM AuthorEntity a WHERE a.age > ?1")
  Iterable<AuthorEntity> findAuthorsWithAgeGreatherThan(int age);

}
