package com.crud.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "copy_id")
    private BookCopy bookCopy;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "borrowed")
    private Date borrowed;

    @Column(name = "returned")
    private Date returned;

    public Loan(Date borrowed) {
        this.borrowed = borrowed;
    }

    public Loan(Long id, BookCopy bookCopy, Reader reader, Date borrowed) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.borrowed = borrowed;
    }
}
