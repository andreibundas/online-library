package org.fasttrackit.onlinelibrary.transfer;

import javax.validation.constraints.NotNull;

public class AddBookToBorrowCartRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;
    @NotNull
    private Long borrowNrWeeks;

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

    public Long getBorrowNrWeeks() {
        return borrowNrWeeks;
    }

    public void setBorrowNrWeeks(Long borrowNrWeeks) {
        this.borrowNrWeeks = borrowNrWeeks;
    }

    @Override
    public String toString() {
        return "AddBookToBorrowCartRequest{" +
                "bookId=" + bookId +
                ", borrowNrWeeks=" + borrowNrWeeks +
                '}';
    }
}
