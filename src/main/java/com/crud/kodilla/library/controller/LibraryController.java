package com.crud.kodilla.library.controller;

import com.crud.kodilla.library.domain.BookCopy;
import com.crud.kodilla.library.domain.BookCopyDto;
import com.crud.kodilla.library.domain.BookDto;
import com.crud.kodilla.library.domain.ReaderDto;
import com.crud.kodilla.library.mapper.BookCopyMapper;
import com.crud.kodilla.library.mapper.BookMapper;
import com.crud.kodilla.library.mapper.ReaderMapper;
import com.crud.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
@CrossOrigin(origins = "*")
public class LibraryController {
    @Autowired
    private DbService dbService;
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        dbService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public BookCopyDto addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        return dbService.saveBookCopy(bookCopyDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBookCopy", consumes = APPLICATION_JSON_VALUE)
    public BookCopyDto updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        return dbService.updateBookCopy(bookCopyDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableCopies")
    public long getTask(@RequestParam Long bookId) {
        List<BookCopy> copies = dbService.getBookCopies(bookId);
        return copies.stream()
                .filter(c -> c.getStatus().equals("in use"))
                .count();
    }
}
