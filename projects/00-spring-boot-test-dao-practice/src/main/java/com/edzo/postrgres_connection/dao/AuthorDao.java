package com.edzo.postrgres_connection.dao;

import java.util.List;
import java.util.Optional;

import com.edzo.postrgres_connection.domain.Author;

public interface AuthorDao {
  void create(Author author);

  Optional<Author> findOne(long id);

  List<Author> findMany();

  void update(long id, Author author);

  void delete(long id);
}
