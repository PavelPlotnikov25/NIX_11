package com.model.product;

public class Telephone extends Product{
    private final String model;
    private final String series;
    private final String screenType;

    public Telephone( String series, String model, String screenType, int price) {
        super(ProductType.TELEPHONE);
        this.model = model;
        this.series = series;
        this.screenType = screenType;
        super.setPrice(price);
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "model='" + model + '\'' +
                ", series='" + series + '\'' +
                ", screenType='" + screenType + '\'' +
                ", price='" + getPrice() + '\'' +
                '}';
    }
}
