package org.fasttrackit.onlinelibrary.transfer.borrowCart;

import java.util.Set;

public class BorrowCartResponse {

    private long id;

    private Set<BookInBorrowCart> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Set<BookInBorrowCart> getBooks() {
        return books;
    }

    public void setBooks(Set<BookInBorrowCart> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BorrowCartResponse{" +
                "id=" + id +
                ", books=" + books +
                '}';
    }
}
