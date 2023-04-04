package com.andersentask.bookshop.book.dtos;

import com.andersentask.bookshop.book.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;

    private String name;

    private BookStatus status;

    private Double price;
}