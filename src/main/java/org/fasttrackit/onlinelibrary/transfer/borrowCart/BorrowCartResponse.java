package org.fasttrackit.onlinelibrary.transfer.borrowCart;

public class BorrowCartResponse {

    private long id;

    // book details to be added later



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BorrowCartResponse{" +
                "id=" + id +
                '}';
    }
}
