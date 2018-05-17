package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    @Override
    Book findOne(Long id);

    @Override
    Book save(Book book);

    @Override
    void delete(Long id);

    Book findByTitleAndAuthorAndYear(String title, String author, int year);
}
