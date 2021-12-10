package org.fasttrackit.onlinelibrary.web;

import org.fasttrackit.onlinelibrary.domain.BorrowCart;
import org.fasttrackit.onlinelibrary.service.BorrowCartService;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.AddBookToBorrowCartRequest;
import org.fasttrackit.onlinelibrary.transfer.borrowCart.BorrowCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
public class BorrowCartController {

    private final BorrowCartService borrowCartService;

    @Autowired
    public BorrowCartController(BorrowCartService borrowCartService) {
        this.borrowCartService = borrowCartService;
    }

    @PutMapping
    public ResponseEntity<Void> addBookToBorrowCart(@RequestBody @Valid AddBookToBorrowCartRequest request) {
        borrowCartService.addBookToBorrowCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<BorrowCartResponse> getBorrowCart(@PathVariable long userId) {
        BorrowCartResponse borrowCartResponse = borrowCartService.getBorrowCart(userId);
        return ResponseEntity.ok(borrowCartResponse);
    }
}

