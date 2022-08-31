package com.repository;

import com.model.Invoice;

import java.util.List;

public interface InvoiceRepository {

    Invoice save(Invoice invoice);

     List<Invoice> findInvoicesWithSumHigher(Double sum);

    Long countOfInvoices();

    void update(Invoice invoice);


    List<String> groupInvoicesBySum();
}
