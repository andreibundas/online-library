package org.fasttrackit.onlinelibrary.service;

import org.fasttrackit.onlinelibrary.domain.BorrowCart;
import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.exception.ResourceNotFoundException;
import org.fasttrackit.onlinelibrary.persistence.BorrowCartRepository;
import org.fasttrackit.onlinelibrary.transfer.AddBookToBorrowCartRequest;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BorrowCartResponse;
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

    // if user does not have a borrow cart, create one and assign it to the user
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

    @Transactional
    public BorrowCartResponse getBorrowCart(long userId) {
        LOGGER.info( "Retrieving borrowCart for user: {}", userId);

        BorrowCart borrowCart = borrowCartRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(" Borrow Cart " + userId + "does not exist "));

        BorrowCartResponse borrowCartResponse = new BorrowCartResponse();
        borrowCartResponse.setId(borrowCart.getId());

        return borrowCartResponse;
    }

}
