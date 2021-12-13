package org.fasttrackit.onlinelibrary.transfer.book;

public class GetBooksRequest {

    private String partialTitle;
    private String partialAuthor;
    private String partialCategories;
    private Double priceWeek;
    private Integer minQuantity;

    public String getPartialTitle() {
        return partialTitle;
    }

    public void setPartialTitle(String partialTitle) {
        this.partialTitle = partialTitle;
    }

    public String getPartialAuthor() {
        return partialAuthor;
    }

    public void setPartialAuthor(String partialAuthor) {
        this.partialAuthor = partialAuthor;
    }

    public String getPartialCategories() {
        return partialCategories;
    }

    public void setPartialCategories(String partialCategories) {
        this.partialCategories = partialCategories;
    }

    public Double getPriceWeek() {
        return priceWeek;
    }

    public void setPriceWeek(Double priceWeek) {
        this.priceWeek = priceWeek;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public String toString() {
        return "GetBooksRequest{" +
                "partialTitle='" + partialTitle + '\'' +
                ", partialAuthor='" + partialAuthor + '\'' +
                ", partialCategories='" + partialCategories + '\'' +
                ", priceWeek=" + priceWeek +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
