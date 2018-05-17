package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookCopyRepositoryTest {
    @Autowired
    BookCopyRepository copyRepository;
    @Autowired
    BookRepository bookRepository;

    @Test
    public void shouldSaveBookCopy() {
        // Given
        Book book = new Book("Great Book", "Great Author", 2001);
        BookCopy copy1 = new BookCopy("in use");
        BookCopy copy2 = new BookCopy("lost");
        book.getBookCopies().add(copy1);
        book.getBookCopies().add(copy2);
        copy1.setBook(book);
        copy2.setBook(book);

        // When
        copyRepository.save(copy1);
        copyRepository.save(copy2);
        Long id1 = copy1.getId();
        Long id2 = copy2.getId();
        BookCopy readCopy1 = copyRepository.findOne(id1);

        // Then
        assertEquals(id1, readCopy1.getId());
        assertNotEquals(new Long(0), id2);
        // assertEquals(2, copyRepository.findAll().size());

        // CleanUp
        copyRepository.delete(id1);
        copyRepository.delete(id2);
        bookRepository.delete(book.getId());
    }
}