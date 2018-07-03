package com.crud.kodilla.library.controller;

import com.crud.kodilla.library.domain.*;
import com.crud.kodilla.library.mapper.BookCopyMapper;
import com.crud.kodilla.library.mapper.BookMapper;
import com.crud.kodilla.library.mapper.LoanMapper;
import com.crud.kodilla.library.mapper.ReaderMapper;
import com.crud.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    private BookCopyMapper bookCopyMapper;
    @Autowired
    private LoanMapper loanMapper;

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
        return bookCopyMapper.mapToBookCopyDto(dbService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBookCopy", consumes = APPLICATION_JSON_VALUE)
    public BookCopyDto updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        return bookCopyMapper.mapToBookCopyDto(dbService.updateBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBookCopy", consumes = APPLICATION_JSON_VALUE)
    public LoanDto borrowBookCopy(@RequestBody LoanDto loanDto) {
        return loanMapper.mapToLoanDto(dbService.saveLoan(loanMapper.mapToLoan(loanDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBookCopy", consumes = APPLICATION_JSON_VALUE)
    public LoanDto returnBookCopy(@RequestBody LoanDto loanDto) {
        Loan loan = dbService.getLoan(loanMapper.mapToLoan(loanDto).getId());
        loan.setReturned(new Date());
        return loanMapper.mapToLoanDto(dbService.updateLoan(loan));
    }

}
