package org.fasttrackit.onlinelibrary.service;

import org.fasttrackit.onlinelibrary.domain.BorrowCart;
import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.persistence.BorrowCartRepository;
import org.fasttrackit.onlinelibrary.transfer.AddBookToBorrowCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BorrowCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowCartService.class);

    private final BorrowCartRepository borrowCartRepository;
    private final UserService userService;

    @Autowired
    public BorrowCartService(BorrowCartRepository borrowCartRepository, UserService userService) {
        this.borrowCartRepository = borrowCartRepository;
        this.userService = userService;
    }

    @Transactional
    public BorrowCart addBooksToBorrowCart(AddBookToBorrowCartRequest request) {
        LOGGER.info("Adding book to borrow-cart: {}", request);

        BorrowCart borrowCart = borrowCartRepository.findById(request.getUserId()).orElse(new BorrowCart());

        if(borrowCart.getUser() == null) {
            User user = userService.getUser(request.getUserId());
            borrowCart.setUser(user);
        }

        //add books to borrowCart

        return borrowCartRepository.save(borrowCart);
    }
}
