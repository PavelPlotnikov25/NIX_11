package com.model.invoice;


import java.util.Comparator;

public class InvoiceComparator <T extends Invoice> implements Comparator<T>  {


    @Override
    public int compare(T o1, T o2) {
        if (o1.getCustomer().getAge() == o2.getCustomer().getAge()){
            if (o1.getProducts().size() == o2.getProducts().size()){
                return Long.compare(o2.getTotalInvoiceSum(), o1.getTotalInvoiceSum());
            }
            return Integer.compare(o1.getProducts().size(), o2.getProducts().size());
        }
        return Integer.compare(o2.getCustomer().getAge(), o1.getCustomer().getAge());
    }
}
