package com.andersentask.bookshop.order.entities;

import com.andersentask.bookshop.book.entities.Book;
import com.andersentask.bookshop.order.enums.OrderStatus;
import com.andersentask.bookshop.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long orderId;

    private User user;

    private BigDecimal orderCost;

    private OrderStatus orderStatus;

    private Timestamp timeOfCompletingOrder;

    private List<Book> booksInOrder;
}
