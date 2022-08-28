package com.service;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.model.Invoice;
import com.model.Product;
import com.model.ProductType;
import com.repository.DB.InvoiceRepository;
import com.repository.hiberante.HibernateInvoiceRepository;

import java.time.LocalDateTime;
import java.util.*;

@Singleton
public class InvoiceService {

    private static InvoiceService instance;
    protected HibernateInvoiceRepository repository;
    private final Random random = new Random();
    @Autowired
    public InvoiceService(HibernateInvoiceRepository invoiceRepository) {
        this.repository = invoiceRepository;
    }


    public static InvoiceService getInstance() {
        if (instance == null) {
            instance = new InvoiceService(HibernateInvoiceRepository.getInstance());
        }
        return instance;
    }



    public Invoice createAndSave (List<Product> products){
        Invoice invoice = new Invoice();
        invoice.setId(UUID.randomUUID().toString());
        invoice.setTime(LocalDateTime.now());
        invoice.setSum(products.stream()
                .mapToDouble(Product::getPrice).sum());
        invoice.setProducts(products);
        repository.save(invoice);
        return invoice;
    }

    public Long countOfInvoices(){
        return repository.countOfInvoices();
    }

    public void UpdateInvoiceTime(Invoice invoice){
        repository.update(invoice);
    }

    public List<Invoice> findInvoicesWithSumHigher(double sum){
        return repository.findInvoicesWithSumHigher(sum);
    }

    public List<String> groupInvoices (){
        return repository.groupInvoicesBySum();
    }

    public List<Product> getProducts(int count, ProductType type){
        List<Product> products = new ArrayList<>();
        if (type.equals(ProductType.PHONE)){
            return PhoneService.getInstance().getFreeProduct(count);
        }
        if (type.equals(ProductType.COMPUTER)){
            return ComputerService.getInstance().getFreeProduct(count);
        }
        if (type.equals(ProductType.TELEVISION)){
            return TelevisionService.getInstance().getFreeProduct(count);
        }
        return products;
    }

    public Invoice setProductFromDataBase(Invoice invoice){
        List<Product> productList = new ArrayList<>();
                ComputerService.getInstance().getAllByInvoiceId(invoice.getId());
                TelevisionService.getInstance().getAllByInvoiceId(invoice.getId());
                PhoneService.getInstance().getAllByInvoiceId(invoice.getId());
        return invoice;
    }

}
