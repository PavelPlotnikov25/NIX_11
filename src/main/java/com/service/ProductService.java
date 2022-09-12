package com.service;

import com.model.Product;
import com.model.ProductType;
import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ProductService<T extends Product> {
    protected static final Random RANDOM = new Random();
    protected CrudRepository<T> repository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    protected ProductService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    private final Predicate<T> verifyThatProductHasPrice = product -> product.getPrice() > 0;

    public List<Product> createAndSaveProduct(int count) {
        List<T> products = new LinkedList<>();
        if (count < 1) {
            throw new IllegalArgumentException("Count must be bigger than 0");
        }
        for (int i = 0; i < count; i++) {
            final T phone = createProduct();
            products.add(phone);
            logger.info("SAVE " + phone);
        }
        repository.saveAll(products);
        Object Product;
        return (List<com.model.Product>) products;
    }

    protected abstract T createProduct();

    public void printAll() {
        for (T product : repository.getAll()) {
            System.out.println(product);
        }
    }

    public void update(Product product) {
        repository.update((T) product);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void save(T product) {
        if (product.getCount() == 0) {
            product.setCount(-1);
        }
        repository.save(product);
    }

    public void printProductHigherThatExpectedPrice(double expetctedPrice) {
        repository.getAll()
                .stream()
                .filter(product -> product.getPrice() >= expetctedPrice)
                .forEach(product -> System.out.println(product + " price: " + product.getPrice()));
    }
    public List<Product> getProductsWithoutInvoice (int count, String productType){
        List<Product> productsForInvoice = new ArrayList<>();
        for (int i = 0; i < count; i++){
            productsForInvoice.add(repository.getAll().get(RANDOM.nextInt(0, repository.getAll().size())));
        }
        return productsForInvoice;
    }

    public int sumOfProductsByReduce() {
        return repository.getAll()
                .stream()
                .filter(product -> verifyThatProductHasPrice.test(product))
                .map(Product::getCount)
                .reduce(0, Integer::sum);
    }

    public Map<String, ProductType> sortProductsToMap() {
        final ProductType[] values = ProductType.values();
        return repository.getAll()
                .stream()
                .distinct()
                .sorted(Comparator.comparing(product -> product.getTitle()))
                .collect(Collectors.toMap(Product::getId, Product::getType, (o1, o2) -> o2));
    }

    public DoubleSummaryStatistics priceStatistics() {
        return repository.getAll()
                .stream()
                .mapToDouble(product -> product.getPrice())
                .summaryStatistics();
    }

    public Product createProductFromMap(Map<String, Object> map) {
        Function<Map<String, Object>, Product> productFunction = (map1) -> {
            Object productType = map.get("productType");
            if (productType instanceof ProductType type) {
                return switch (type) {
                    case PHONE -> new Phone(map.getOrDefault("title", "Default").toString(),
                            (Integer) map.getOrDefault("count", 0),
                            (Double) map.getOrDefault("price", 0.0),
                            map.getOrDefault("model", "default model").toString(),
                            Manufacturer.valueOf(map.getOrDefault("manufacturer", Manufacturer.APPLE).toString()));
                    case COMPUTER -> new Computer(map.getOrDefault("Title", "Default title").toString(),
                            (Integer) map.getOrDefault("count", 0),
                            (Double) map.getOrDefault("Price", 0),
                            map.getOrDefault("Model", "Default Model").toString(),
                            ManufacturerComputer.valueOf(map.getOrDefault("Manufacturer", ManufacturerComputer.APPLE).toString()),
                            null);
                    case TELEVISION -> new Television(map.getOrDefault("Title", "Default title").toString(),
                            (Integer) map.getOrDefault("count", 0),
                            (Double) map.getOrDefault("Price", 0),
                            map.getOrDefault("Model", "Default Model").toString(),
                            ManufacturerTelevision.valueOf(map.getOrDefault("Manufacturer", ManufacturerTelevision.SAMSUNG).toString()),
                            (Integer) map.getOrDefault("diagonal", 0));
                };
            }else {
                throw new IllegalArgumentException();
            }
        };
        return productFunction.apply(map);
    }
}







