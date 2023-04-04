package com.andersentask.bookshop.services.interfaces;

import com.andersentask.bookshop.dtos.BookDTO;
import com.andersentask.bookshop.enums.BookStatus;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    List<BookDTO> getBooksSortedByName();

    List<BookDTO> getBooksSortedByPrice();

    List<BookDTO> getBooksSortedByAvailability();
}
