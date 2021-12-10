package org.fasttrackit.onlinelibrary.service;

import org.fasttrackit.onlinelibrary.domain.Book;
import org.fasttrackit.onlinelibrary.domain.BorrowCart;
import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.exception.ResourceNotFoundException;
import org.fasttrackit.onlinelibrary.persistence.BorrowCartRepository;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.AddBookToBorrowCartRequest;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BookInBorrowCart;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BorrowCartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class BorrowCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowCartService.class);

    private final BorrowCartRepository borrowCartRepository;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BorrowCartService(BorrowCartRepository borrowCartRepository, UserService userService, BookService bookService) {
        this.borrowCartRepository = borrowCartRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    // if user does not have a borrow cart, create one and assign it to the user
    @Transactional
    public void addBookToBorrowCart(AddBookToBorrowCartRequest request) {
        LOGGER.info("Adding book to borrow-cart: {}", request);

        BorrowCart borrowCart = borrowCartRepository.findById(request.getUserId()).orElse(new BorrowCart());

        if(borrowCart.getUser() == null) {
            User user = userService.getUser(request.getUserId());
            borrowCart.setUser(user);
        }

        Book book = bookService.getBook(request.getBookId());
        borrowCart.addBook(book);

        borrowCartRepository.save(borrowCart);
    }

    @Transactional
    public BorrowCartResponse getBorrowCart(long userId) {
        LOGGER.info( "Retrieving borrowCart for user: {}", userId);

        BorrowCart borrowCart = borrowCartRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(" Borrow Cart " + userId + "does not exist "));

        BorrowCartResponse borrowCartResponse = new BorrowCartResponse();
        borrowCartResponse.setId(borrowCart.getId());

        Set<BookInBorrowCart> books = new HashSet<>();

        for (Book book : borrowCart.getBooks()) {
            BookInBorrowCart bookInBorrowCart = new BookInBorrowCart();
            bookInBorrowCart.setId(book.getId());
            bookInBorrowCart.setTitle(book.getTitle());
            bookInBorrowCart.setImageUrl(book.getImageUrl());
            bookInBorrowCart.setPriceWeek(book.getPriceWeek());

            books.add(bookInBorrowCart);
        }

        borrowCartResponse.setBooks(books);

        return borrowCartResponse;
    }
}
