package com.service;

import com.model.customer.Customer;
import com.model.invoice.Invoice;
import com.model.invoice.InvoiceType;
import com.model.product.Product;
import com.model.product.ProductType;
import com.model.product.Telephone;
import com.model.product.Television;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    private ShopService target;
    private List<Product> productList;
    private List<Invoice> invoices;
    private Date date;

    @BeforeEach
    void setUp() {
    target = new ShopService();
        Product product = new Telephone("11Pro", "Apple", "OLED", 1000 );
        Product product1 = new Telephone("12Pro", "Apple", "OLED", 1200 );
        Product product2 = new Telephone("13Pro", "Apple", "OLED", 1000 );
        product.setProductType(ProductType.TELEPHONE);
        product1.setProductType(ProductType.TELEPHONE);
        product2.setProductType(ProductType.TELEPHONE);
        productList = List.of(product2,product1,product);

    }


    @Test
    void createProductFromString() throws IOException {
        Product product = new Telephone("11Pro", "Apple", "OLED", 1000 );
       Product product1 =  target.createProductFromString("Telephone,11Pro,Apple,none,OLED,none,1000");
        assertEquals(product.getPrice(), product1.getPrice());
    }
    @Test
    void createProductFromStringNegative() throws IOException {
        Product product = new Telephone("11Pro", "Apple", "OLED", 1000 );
        Product product1 =  target.createProductFromString("Telephone,11Pro,Apple,none,OLED,none,100");
        assertNotEquals(product.getPrice(), product1.getPrice());
    }

    @Test
    void createInvoices() {
        invoices = target.createInvoices(PersonService.createNewCustomer(), productList, 10,2);
        Assertions.assertTrue(invoices.size() == 2);
    }

    @Test
    void createInvoicesNegative() {

        invoices = target.createInvoices(PersonService.createNewCustomer(), productList, 10,2);
    }

    @Test
    void sumOfProducts() {
        invoices = target.createInvoices(PersonService.createNewCustomer(), productList, 10,1);
        long result = target.sumOfProducts(ProductType.TELEPHONE);
        Assertions.assertTrue(result <= 5);
    }

    @Test
    void sumOfAllInvoices() {
        ArrayList<Product> productList1 = new ArrayList<>();
       productList1.add(new Telephone("11Pro", "Apple", "OLED", 1000 ));
        invoices = target.createInvoices(PersonService.createNewCustomer(), productList1, 10,1);
        long allInvoices = target.sumOfAllInvoices();
        Assertions.assertEquals(1000, allInvoices);
    }




    @Test
    void totalInvoiceSum() {
        Product product = new Telephone("11Pro", "Apple", "OLED", 10 );
        Product product2 = new Telephone("11Pro", "Apple", "OLED", 10 );
        Invoice invoice = new Invoice(List.of(product, product2), PersonService.createNewCustomer(), new Date(), InvoiceType.RETAIL);
        target.totalInvoiceSum(invoice.getProducts());
        Assertions.assertTrue(target.totalInvoiceSum(invoice.getProducts()) == 20);
    }

    @Test
    void totalInvoiceSumNegative() {
        Product product = new Telephone("11Pro", "Apple", "OLED", 10 );
        Product product2 = new Telephone("11Pro", "Apple", "OLED", 10 );
        Invoice invoice = new Invoice(List.of(product, product2), PersonService.createNewCustomer(), new Date(), InvoiceType.RETAIL);
        int expectedSum = 10;
        Assertions.assertNotEquals(expectedSum,  target.totalInvoiceSum(invoice.getProducts()));
    }

    @Test
    void createProducts() {
    }
}
