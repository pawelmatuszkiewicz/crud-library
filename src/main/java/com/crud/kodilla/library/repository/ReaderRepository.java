package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
    @Override
    List<Reader> findAll();

    @Override
    Reader findOne(Long id);

    @Override
    Reader save(Reader reader);

    @Override
    void delete(Long id);
}
