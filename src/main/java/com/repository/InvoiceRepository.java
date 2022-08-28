package com.repository;

import com.Main;
import com.model.Invoice;
import com.model.Product;

import java.util.List;
import java.util.Map;

public interface InvoiceRepository {

    Invoice save(Invoice invoice);

     List<Invoice> findInvoicesWithSumHigher(Double sum);

    int countOfInvoices();

    void update(Invoice invoice);


    List<String> groupInvoicesBySum();
}
