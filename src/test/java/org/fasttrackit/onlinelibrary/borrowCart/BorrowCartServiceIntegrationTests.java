package org.fasttrackit.onlinelibrary.borrowCart;

import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.service.BorrowCartService;
import org.fasttrackit.onlinelibrary.steps.BookTestSteps;
import org.fasttrackit.onlinelibrary.steps.UserTestSteps;
import org.fasttrackit.onlinelibrary.transfer.book.BookResponse;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.AddBookToBorrowCartRequest;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BookInBorrowCart;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BorrowCartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
public class BorrowCartServiceIntegrationTests {

    @Autowired
    private BorrowCartService borrowCartService;

    @Autowired
    private UserTestSteps userTestSteps;

    @Autowired
    private BookTestSteps bookTestSteps;

    @Test
    public void addBookToBorrowCart_whenValidRequest_thenBooksAreAddedToBorrowCart() {

        User user = userTestSteps.createUser();
        BookResponse book = bookTestSteps.createBook();

        AddBookToBorrowCartRequest request = new AddBookToBorrowCartRequest();
        request.setUserId(user.getId());
        request.setBookId(book.getId());

        borrowCartService.addBookToBorrowCart(request);

        BorrowCartResponse borrowCart = borrowCartService.getBorrowCart(user.getId());

        assertThat(borrowCart, notNullValue());
        assertThat(borrowCart.getId(), is(request.getUserId()));

        assertThat(borrowCart.getBooks(), notNullValue());
        assertThat(borrowCart.getBooks(), hasSize(1));

        BookInBorrowCart bookInCart = borrowCart.getBooks().iterator().next();

        assertThat(bookInCart, notNullValue());
        assertThat(bookInCart.getId(), is(book.getId()));
        assertThat(bookInCart.getTitle(), is(book.getTitle()));
        assertThat(bookInCart.getImageUrl(), is(book.getImageUrl()));
        assertThat(bookInCart.getPriceWeek(), is(book.getPriceWeek()));

    }

}
