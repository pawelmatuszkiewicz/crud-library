package com.crud.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
}
