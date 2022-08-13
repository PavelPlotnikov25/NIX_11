package com;

import com.model.product.ProductType;
import com.service.PersonService;
import com.service.ShopService;

import java.io.IOException;

public class Main {
    PersonService personService = new PersonService();
    public static void main(String[] args) throws IOException {
        ShopService service = new ShopService();
        System.out.println("Creating 15 invoices " + System.lineSeparator());
        service.createInvoices(PersonService.createNewCustomer(), service.createProducts("Product.csv"), 2000, 15);
        System.out.println(System.lineSeparator());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        service.sumOfProducts(ProductType.TELEPHONE);
        service.sumOfProducts(ProductType.TELEVISION);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());

        System.out.println("Display a sum of all invoices");
        service.sumOfAllInvoices();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());

        System.out.println("Display the cheapest invoice and customer info");
        service.cheapestInvoice();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());

        System.out.println("Display invoices, which have only one type of product ");
        service.specificProductInvoices();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());

        System.out.println("Display invoices with customer's age lower 18");
        service.youngCustomer();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());

        System.out.println("Display first three invoices");
        service.firstThreeInvoices();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());

        System.out.println("Display sorted invoices");
        service.sortedInvoice();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(System.lineSeparator());
        System.out.println("Display retail Invoices");
        service.retailInvoices();


    }
}
