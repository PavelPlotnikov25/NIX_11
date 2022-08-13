package com.service;

import com.exception.InvalidStringException;
import com.model.customer.Customer;
import com.model.invoice.Invoice;
import com.model.invoice.InvoiceComparator;
import com.model.invoice.InvoiceType;
import com.model.product.Product;
import com.model.product.ProductType;
import com.model.product.Telephone;
import com.model.product.Television;
import com.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



public class ShopService {
    List <Product> products = new ArrayList();
    List<Invoice> invoices = new ArrayList();
    final String regexForCsv = "(T\\w+?),+(.*?),(.*?),(.*?),(.*?),(.*?),(.*)";
    Random random = new Random();
    Date date = new Date();
    InvoiceType invioceType;
    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

    public List<Product> createProducts(String fileName) throws IOException {
        List<String> dataList = Utils.getListString(fileName);
        List<Product> products = new ArrayList();
        int count  = 1;
        for (String line:dataList) {
            if (count != 1){
                try {
                    Product product = createProductFromString(line);
                    products.add(product);
                }catch (InvalidStringException e){
                    System.out.println("INVALID STRING " + count);
                }
            }
            count++;
        }
        return products;
    }
    public Product createProductFromString(String product) throws IOException {
        Product result = null;
        Pattern pattern = Pattern.compile(regexForCsv);
        Matcher matcher = pattern.matcher(product);

        if (matcher.find()){
            try {
                if (matcher.group(1).equals(ProductType.TELEPHONE.getValue())) {
                    result = new Telephone(matcher.group(2),
                            matcher.group(3),
                            matcher.group(5),
                            Integer.parseInt(matcher.group(7)));
                    result.setProductType(ProductType.TELEPHONE);


                }else if (matcher.group(1).equals(ProductType.TELEVISION.getValue())){
                    result = new Television(matcher.group(2),
                            Integer.parseInt(matcher.group(4)),
                            matcher.group(5),
                            matcher.group(6),
                            Integer.parseInt(matcher.group(7)));
                    result.setProductType(ProductType.TELEVISION);

                }
            }catch (Exception e){
                throw new InvalidStringException("INVALID STRING");
            }
        }else {
            throw new InvalidStringException("INVALID STRING");
        }
        return result;
    }
    public List<Invoice> createInvoices(Customer customer, List<Product> products, int limit, int count){

            for (int j = 0; j< count; j++) {
                customer = PersonService.createNewCustomer();
                int randomCount = random.nextInt(1, 5);
                List<Product> listForInvoice = new ArrayList();
                for (int i = 0; i < randomCount; i++) {
                    Product randomProduct = products.get(random.nextInt(products.size()));
                    listForInvoice.add(randomProduct);
                }
                Invoice invoice = new Invoice(listForInvoice, customer, new Date(date.getTime()), setProductsInvoiceType(limit, listForInvoice));
                invoice.setTotalInvoiceSum(totalInvoiceSum(listForInvoice));
                invoice.setInvoiceType(invioceType);
                invoices.add(invoice);
                logger.info("INVOICE DATE [ "+ invoice.getCreatingDate()+ "]    Customer data [ " + invoice.getCustomer() + " ]       Invoice data" + invoice.getProducts() + " ]");
            }
            return invoices;
    }

    public long sumOfProducts(ProductType productType){
        long countOfProducts = invoices.stream()
                .flatMap(invoice -> invoice.getProducts().stream())
                .filter(product -> product.getProductType().getValue().equals(productType.getValue()))
                .count();
        System.out.println("We have - " + countOfProducts + " sold " + productType + "S");
        return countOfProducts;
    }

    public long sumOfAllInvoices(){
       long sum =  invoices.stream()
                .flatMap(invoice -> invoice.getProducts().stream())
                .map(product -> product.getPrice())
                .reduce(0, (o1, o2) -> o1 + o2);
        System.out.println("We sold produtcs for " + sum + " $!");
        return sum;
    }

    public void cheapestInvoice(){
        invoices.stream()
                .collect(Collectors.toMap(invoice -> invoice.getProducts().stream()
                        .map(product -> product.getPrice())
                        .reduce(0, (o1, o2) -> o1 + o2),
                        Invoice::getCustomer,(o1, o2) -> o1,HashMap::new))
                .entrySet().stream()
                .findFirst()
                .ifPresent(o -> System.out.println("The lowest invoice sum is "
                        + o.getKey()
                        + " By - "
                        + o.getValue()));
    }

    public void specificProductInvoices(){
        List<Invoice> specificInvoice = new ArrayList<>();
        invoices.stream().forEach(invoice -> {
            if (checkInvoice(invoice.getProducts(), ProductType.TELEPHONE)){
                specificInvoice.add(invoice);
            }
            if (checkInvoice(invoice.getProducts(), ProductType.TELEVISION)){
                specificInvoice.add(invoice);
            }
        });
        specificInvoice.stream().forEach(System.out::println);

    }
    private boolean checkInvoice (List<Product> products, ProductType productType){
        return products.stream()
                .allMatch(product -> product.getProductType().getValue().equals(productType.getValue()));
    }

    public void youngCustomer() {
        List<Invoice> youngCustomerInvoices = new ArrayList<>();
        youngCustomerInvoices = invoices.stream()
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
                .collect(Collectors.toList());
        youngCustomerInvoices.stream()
                .forEach(invoice -> {
                    invoice.setInvoiceType(InvoiceType.LOW_AGE);
                    System.out.println(invoice);
                });
    }

    public void firstThreeInvoices(){
        invoices.stream()
                .sorted(Comparator.comparing(Invoice -> Invoice.getCreatingDate()))
                .limit(3)
                .forEach(System.out::println);
    }

    public void sortedInvoice(){
        InvoiceComparator invoiceComparator = new InvoiceComparator<>();
        invoices.stream()
                .sorted(invoiceComparator)
                .forEach(System.out::println);
    }

    public void retailInvoices(){
        invoices.stream()
                .filter(invoice -> invoice.getInvoiceType() == InvoiceType.RETAIL)
                .forEach(System.out::println);
    }

    private InvoiceType setProductsInvoiceType(int limit, List<Product> products) {
        if (totalInvoiceSum(products) > limit){
            invioceType = InvoiceType.WHOLESALE;
        }else if (totalInvoiceSum(products) <= limit){
            invioceType = InvoiceType.RETAIL;
        }
        return invioceType;
    }
    public long totalInvoiceSum(List<Product> products) {
        long sum = products.stream()
                .mapToLong(Product::getPrice)
                .sum();
        return sum;
    }
}
