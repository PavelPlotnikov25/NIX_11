package com.model.product;

public class Television extends Product{

    private final String screenType;
    private final String series;
    private final int diagonal;
    private final String country;
    private ProductType productType;

    public Television(String series, int diagonal,String screenType,  String country, int price) {
        super(ProductType.TELEVISION);
        this.series = series;
        this.diagonal = diagonal;
        this.screenType = screenType;
        this.country = country;
        super.setPrice(price);
    }

    @Override
    public String toString() {
        return "Television{" +
                "screenType='" + screenType + '\'' +
                ", series='" + series + '\'' +
                ", diagonal=" + diagonal +
                ", country='" + country + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
