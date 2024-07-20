package com.edzo.database.services;

import java.util.List;
import java.util.Optional;

import com.edzo.database.domains.entities.AuthorEntity;

public interface AuthorService {
  AuthorEntity save(AuthorEntity author);

  List<AuthorEntity> findAll();

  Optional<AuthorEntity> findById(Long id);

  boolean isExists(Long id);

  AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

  void delete(Long id);
}
