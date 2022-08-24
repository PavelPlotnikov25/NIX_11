package com;

import com.model.Invoice;
import com.model.Product;
import com.model.ProductType;
import com.program.Program;
import com.repository.DB.DBComputerRepository;
import com.repository.DB.DBPhoneRepository;
import com.repository.DB.DBTelevisionRepository;
import com.repository.DB.InvoiceRepository;
import com.service.ComputerService;
import com.service.InvoiceService;
import com.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {


    public static void main(String[] args) {
//        Program program = new Program();
//        program.run();

        InvoiceService invoiceService = InvoiceService.getInstance();
        System.out.println("JDBC TESTING");
        List<Product> productsForInvoice1 = invoiceService.getProducts(1, ProductType.COMPUTER);
        productsForInvoice1.addAll(invoiceService.getProducts(1,ProductType.PHONE));
        invoiceService.createAndSave(productsForInvoice1);
        List<Product> productsForInvoice2 = invoiceService.getProducts(1, ProductType.COMPUTER);
       productsForInvoice2.addAll(invoiceService.getProducts(2, ProductType.TELEVISION));
        productsForInvoice2.addAll(invoiceService.getProducts(1, ProductType.PHONE));
        System.out.println("Count of invoices  - " + invoiceService.countOfInvoices());
        System.out.println("Group inivoices by sum - " + invoiceService.groupInvoices());
        System.out.println("Invoices with sum higher than 1000 - " + invoiceService.findInvoicesWithSumHigher(1000));
        List<Product> productsForInvoice3 = invoiceService.getProducts(1, ProductType.COMPUTER);
        productsForInvoice1.addAll(invoiceService.getProducts(2,ProductType.PHONE));
        Invoice invoice = invoiceService.createAndSave(productsForInvoice3);
        invoiceService.UpdateInvoiceTime(invoice);
        System.out.println(invoice);

    }
}
