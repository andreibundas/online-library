package org.fasttrackit.onlinelibrary.steps;

import org.fasttrackit.onlinelibrary.service.BookService;
import org.fasttrackit.onlinelibrary.transfer.book.BookResponse;
import org.fasttrackit.onlinelibrary.transfer.book.SaveBookRequst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@Component
public class BookTestSteps {

    @Autowired
    private BookService bookService;

    public BookResponse createBook() {

        SaveBookRequst request = new SaveBookRequst();

        request.setTitle("Title" + System.currentTimeMillis());
        request.setAuthor("Author" + System.currentTimeMillis());
        request.setCategories("Category" + System.currentTimeMillis());
        request.setBorrowWeeks(3);
        request.setPriceWeek(5.0);
        request.setQuantity(5);
        request.setImageUrl("Image");

        BookResponse book = bookService.createBook(request);

        assertThat(book, notNullValue());
        assertThat(book.getId(), notNullValue());
        assertThat(book.getId(), greaterThan(0L));
        assertThat(book.getTitle(), is(request.getTitle()));
        assertThat(book.getAuthor(), is(request.getAuthor()));
        assertThat(book.getCategories(), is(request.getCategories()));
        assertThat(book.getBorrowWeeks(), is(request.getBorrowWeeks()));
        assertThat(book.getPriceWeek(), is(request.getPriceWeek()));
        assertThat(book.getQuantity(), is(request.getQuantity()));
        assertThat(book.getImageUrl(), is(request.getImageUrl()));

        return book;
    }

}
