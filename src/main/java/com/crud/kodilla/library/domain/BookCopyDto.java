package com.crud.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookCopyDto {
    private Long id;
    private String status;
    private Long bookId;
}
