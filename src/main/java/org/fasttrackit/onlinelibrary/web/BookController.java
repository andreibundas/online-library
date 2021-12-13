package org.fasttrackit.onlinelibrary.web;

import org.fasttrackit.onlinelibrary.service.BookService;
import org.fasttrackit.onlinelibrary.transfer.book.BookResponse;
import org.fasttrackit.onlinelibrary.transfer.book.GetBooksRequest;
import org.fasttrackit.onlinelibrary.transfer.book.SaveBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid SaveBookRequest request) {
        BookResponse book = bookService.createBook(request);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable long id) {
        BookResponse bookResponse = bookService.getBookResponse(id);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getBooks(GetBooksRequest request, Pageable pageable) {
        Page<BookResponse> books = bookService.getBooks(request, pageable);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

//    @PutMapping("/{id}")  -> Fetch Type Lazy problem
//    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody @Valid SaveBookRequest request) {
//        Book book = bookService.updateBook(id, request);
//        return ResponseEntity.ok(book);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable long id, @RequestBody @Valid SaveBookRequest request) {
        BookResponse book = bookService.updateBookResponse(id, request);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
