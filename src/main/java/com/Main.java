package com;

import com.model.Product;
import com.model.ProductType;

import com.model.computer.Computer;
import com.model.phone.Phone;

import com.model.television.Television;
import com.service.*;

import java.util.List;
import java.util.Random;


public class Main {


    public static void main(String[] args) {
        Random random = new Random();
//        Program program = new Program();
//   program.run();
        ProductService<Phone> phoneProductService = PhoneService.getInstance();
        ProductService<Computer> computerProductService = ComputerService.getInstance();
        ProductService<Television> televisionProductService = TelevisionService.getInstance();
        InvoiceService invoiceService = InvoiceService.getInstance();

        phoneProductService.createAndSaveProduct(30);
        computerProductService.createAndSaveProduct(30);
        televisionProductService.createAndSaveProduct(30);


        for (int i = 0; i < 10; i++){
            List<Product> productsForInvoice = invoiceService.getProducts(random.nextInt(0,5), ProductType.COMPUTER);
            productsForInvoice.addAll(invoiceService.getProducts(random.nextInt(0,5),ProductType.PHONE));
            productsForInvoice.addAll(invoiceService.getProducts(random.nextInt(0,5),ProductType.TELEVISION));
            invoiceService.createAndSave(productsForInvoice);
        }

        System.out.println("Count of invoices  - " + invoiceService.countOfInvoices());
        System.out.println("Group inivoices by sum - " + invoiceService.groupInvoices());
        System.out.println("Invoices with sum higher than 1000 - " + invoiceService.findInvoicesWithSumHigher(1000));
    }
}
