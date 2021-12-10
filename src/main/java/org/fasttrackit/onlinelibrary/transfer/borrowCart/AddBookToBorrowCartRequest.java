package org.fasttrackit.onlinelibrary.transfer.borrowCart;

import javax.validation.constraints.NotNull;

public class AddBookToBorrowCartRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


    @Override
    public String toString() {
        return "AddBookToBorrowCartRequest{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
