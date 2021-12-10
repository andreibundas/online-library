package org.fasttrackit.onlinelibrary.transfer.book;

public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String categories;
    private Double priceWeek;
    private Integer borrowWeeks;
    private String imageUrl;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Double getPriceWeek() {
        return priceWeek;
    }

    public void setPriceWeek(Double priceWeek) {
        this.priceWeek = priceWeek;
    }

    public Integer getBorrowWeeks() {
        return borrowWeeks;
    }

    public void setBorrowWeeks(Integer borrowWeeks) {
        this.borrowWeeks = borrowWeeks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categories='" + categories + '\'' +
                ", priceWeek=" + priceWeek +
                ", borrowWeeks=" + borrowWeeks +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
