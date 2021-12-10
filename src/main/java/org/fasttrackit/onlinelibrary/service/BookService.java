package org.fasttrackit.onlinelibrary.service;
import org.fasttrackit.onlinelibrary.domain.Book;
import org.fasttrackit.onlinelibrary.exception.ResourceNotFoundException;
import org.fasttrackit.onlinelibrary.persistence.BookRepository;
import org.fasttrackit.onlinelibrary.transfer.book.SaveBookRequst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(SaveBookRequst requst) {
        LOGGER.info("Creating Book: {} ", requst);

        Book book = new Book();
        book.setAuthor(requst.getAuthor());
        book.setBorrowWeeks(requst.getBorrowWeeks());
        book.setCategories(requst.getCategories());
        book.setImageUrl(requst.getImageUrl());
        book.setPriceWeek(requst.getPriceWeek());
        book.setQuantity(requst.getQuantity());

        return bookRepository.save(book);
    }

    public Book getBook(long id) {
        LOGGER.info("Retrieving book {}", id);

        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book " + id + " does not exist"));
    }
}
