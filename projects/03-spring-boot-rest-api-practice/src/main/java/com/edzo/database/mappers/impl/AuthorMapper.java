package com.edzo.database.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edzo.database.domains.dtos.AuthorDto;
import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.mappers.Mapper;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDto> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public AuthorDto mapTo(AuthorEntity authorEntity) {
    return modelMapper.map(authorEntity, AuthorDto.class);
  }

  @Override
  public AuthorEntity mapFrom(AuthorDto b) {
    return modelMapper.map(b, AuthorEntity.class);
  }

}
