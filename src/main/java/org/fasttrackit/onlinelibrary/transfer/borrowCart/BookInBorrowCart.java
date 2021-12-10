package org.fasttrackit.onlinelibrary.transfer.borrowCart;

import javax.validation.constraints.NotNull;

public class BookInBorrowCart {

    private long id;
    private String title;
    private double priceWeek;
    private String imageUrl;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPriceWeek() {
        return priceWeek;
    }

    public void setPriceWeek(double priceWeek) {
        this.priceWeek = priceWeek;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BookInBorrowCart{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priceWeek=" + priceWeek +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
