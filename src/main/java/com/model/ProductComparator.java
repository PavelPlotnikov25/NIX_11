package com.model;

import java.util.Comparator;

public class ProductComparator <T extends Product> implements Comparator<T>  {

    @Override
    public int compare(T o1, T o2) {
        if (o1.getPrice()== o2.getPrice()){
            if (o1.getTitle().equals(o2.getTitle())){
                return Integer.compare(o1.getCount(), o2.getCount());
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
