package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void shouldSaveBook() {
        // Given
        Book book = new Book("test_book", "test_author", 2002);

        // When
        bookRepository.save(book);

        // Then
        Long id = book.getId();
        Book readBook = bookRepository.findOne(id);
        assertEquals(id, readBook.getId());

        // CleanUp
        bookRepository.delete(id);
    }

    @Test
    public void shouldSaveBookAndBookCopies() {
        // Given
        Book book = new Book("Great Book", "Smart Author", 1999);
        BookCopy copy1 = new BookCopy("in use");
        BookCopy copy2 = new BookCopy("in use");
        book.getBookCopies().add(copy1);
        book.getBookCopies().add(copy2);
        copy1.setBook(book);
        copy2.setBook(book);

        // When
        bookRepository.save(book);
        Long id = book.getId();
        Book readBook = bookRepository.findOne(id);

        // Then
        assertNotEquals(new Long(0), id);
        assertEquals(id, readBook.getId());

        // CleanUp
        bookRepository.delete(id);
    }
}