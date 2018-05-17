package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    @Override
    List<BookCopy> findAll();

    @Override
    BookCopy findOne(Long id);

    @Override
    BookCopy save(BookCopy bookCopy);

    @Override
    void delete(Long id);
}
