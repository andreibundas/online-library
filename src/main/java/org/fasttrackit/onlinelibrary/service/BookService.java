package org.fasttrackit.onlinelibrary.service;
import org.fasttrackit.onlinelibrary.domain.Book;
import org.fasttrackit.onlinelibrary.exception.ResourceNotFoundException;
import org.fasttrackit.onlinelibrary.persistence.BookRepository;
import org.fasttrackit.onlinelibrary.transfer.book.BookResponse;
import org.fasttrackit.onlinelibrary.transfer.book.GetBooksRequest;
import org.fasttrackit.onlinelibrary.transfer.book.SaveBookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(SaveBookRequest requst) {
        LOGGER.info("Creating Book: {} ", requst);

        Book book = new Book();
        book.setTitle(requst.getTitle());
        book.setAuthor(requst.getAuthor());
        book.setBorrowWeeks(requst.getBorrowWeeks());
        book.setCategories(requst.getCategories());
        book.setImageUrl(requst.getImageUrl());
        book.setPriceWeek(requst.getPriceWeek());
        book.setQuantity(requst.getQuantity());

        Book savedBook = bookRepository.save(book);

        return mapBookResponse(savedBook);
    }

    public Book getBook(long id) {  // do not use it from controller !
        LOGGER.info("Retrieving book {}", id);

        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book " + id + " does not exist"));
    }

    public BookResponse getBookResponse(long id) {
        LOGGER.info("Retrieving book response {}: ", id);
        Book book = getBook(id);

        return mapBookResponse(book);
    }
// query by example
    public Page<BookResponse> getBooks (GetBooksRequest request, Pageable pageable) {
        LOGGER.info("Retrieving books: {}", request);

        Book exampleBook = new Book();
        exampleBook.setTitle(request.getPartialTitle());
        exampleBook.setAuthor(request.getPartialAuthor());
        exampleBook.setCategories(request.getPartialCategories());
        exampleBook.setQuantity(request.getMinQuantity());
        exampleBook.setPriceWeek(request.getPriceWeek());
        exampleBook.setBorrowCarts(null);

        Example<Book> example = Example.of(exampleBook,
                ExampleMatcher.matchingAny()
                        .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()));
        // todo: add matcher for minimum quantity

        Page<Book> booksPage = bookRepository.findAll(example, pageable);

        List<BookResponse> booksDtos = new ArrayList<>();

        for(Book book : booksPage.getContent()) {
            BookResponse bookResponse = mapBookResponse(book);

            booksDtos.add(bookResponse);
        }

        return new PageImpl<>(booksDtos, pageable, booksPage.getTotalElements());
    }

    public Book updateBook(long id, SaveBookRequest request) {
        LOGGER.info("Updating book: {} {} ", id, request);

        Book existingBook = getBook(id);

        BeanUtils.copyProperties(request, existingBook);

        return bookRepository.save(existingBook);
    }

    public BookResponse updateBookResponse(long id, SaveBookRequest request) {
        LOGGER.info("Updating book (bookResponse) : {} {} ", id, request);
        Book book = updateBook(id, request);

        return mapBookResponse(book);
    }

    public void deleteBook(long id) {
        LOGGER.info("Deleting book: {}", id);

        bookRepository.deleteById(id);
    }

    private BookResponse mapBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setCategories(book.getCategories());
        bookResponse.setBorrowWeeks(book.getBorrowWeeks());
        bookResponse.setPriceWeek(book.getPriceWeek());
        bookResponse.setImageUrl(book.getImageUrl());
        bookResponse.setQuantity(book.getQuantity());

        return bookResponse;
    }
}
