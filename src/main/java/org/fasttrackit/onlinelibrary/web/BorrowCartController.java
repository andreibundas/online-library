package org.fasttrackit.onlinelibrary.web;

import org.fasttrackit.onlinelibrary.domain.BorrowCart;
import org.fasttrackit.onlinelibrary.service.BorrowCartService;
import org.fasttrackit.onlinelibrary.transfer.AddBookToBorrowCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<BorrowCart> addBooksToBorrowCart(@RequestBody @Valid AddBookToBorrowCartRequest request) {
        BorrowCart borrowCart = borrowCartService.addBooksToBorrowCart(request);
        return ResponseEntity.ok(borrowCart);
    }
}
