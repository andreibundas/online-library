package org.fasttrackit.onlinelibrary.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    private String author;
    private String categories;
    @NotNull
    private Double priceWeek;
    @NotNull
    private Integer borrowWeeks;
    private String imageUrl;
    @NotNull
    private Integer quantity;

    @ManyToMany(mappedBy = "books")
    private Set<BorrowCart> borrowCarts = new HashSet<>();

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

    public Set<BorrowCart> getBorrowCarts() {
        return borrowCarts;
    }

    public void setBorrowCarts(Set<BorrowCart> borrowCarts) {
        this.borrowCarts = borrowCarts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
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
