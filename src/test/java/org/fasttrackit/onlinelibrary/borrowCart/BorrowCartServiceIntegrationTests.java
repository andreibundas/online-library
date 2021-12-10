package org.fasttrackit.onlinelibrary.borrowCart;

import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.service.BorrowCartService;
import org.fasttrackit.onlinelibrary.steps.UserTestSteps;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.AddBookToBorrowCartRequest;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BorrowCartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class BorrowCartServiceIntegrationTests {

    @Autowired
    private BorrowCartService borrowCartService;

    @Autowired
    private UserTestSteps userTestSteps;

    @Test
    public void addBookToBorrowCart_whenValidRequest_thenBooksAreAddedToBorrowCart() {

        User user = userTestSteps.createUser();

        AddBookToBorrowCartRequest request = new AddBookToBorrowCartRequest();
        request.setUserId(user.getId());
        request.setBookId(1L);

        borrowCartService.addBookToBorrowCart(request);

//        BorrowCartResponse borrowCart = borrowCartService.getBorrowCart(request.getUserId());
        BorrowCartResponse borrowCart = borrowCartService.getBorrowCart(user.getId());

        assertThat(borrowCart, notNullValue());
        assertThat(borrowCart.getId(), is(request.getUserId()));

        // cartService.addProductToCart()

    }

}
