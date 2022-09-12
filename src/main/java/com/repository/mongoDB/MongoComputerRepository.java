package com.repository.mongoDB;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.config.MongoDBConfig;
import com.google.gson.*;
import com.model.Product;
import com.model.computer.Computer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.repository.CrudRepository;
import org.bson.Document;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
@Singleton
public class MongoComputerRepository implements CrudRepository<Computer> {

    private static MongoComputerRepository instance;
    private static final MongoDatabase mongoDatabase = MongoDBConfig.getMongoDatabase();
    private static MongoCollection<Document> collection;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)))
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsString() + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withLocale(Locale.ENGLISH)))
            .excludeFieldsWithoutExposeAnnotation().create();

    public MongoComputerRepository() {
        collection = mongoDatabase.getCollection(Computer.class.getSimpleName());
    }

    public static MongoComputerRepository getInstance(){
        if (instance == null){
            instance = new MongoComputerRepository();
        }
        return instance;
    }


    @Override
    public Product save(Computer product) {
        collection.insertOne(Document.parse(gson.toJson(product)));
        return product;
    }

    @Override
    public void saveAll(List<Computer> products) {
        List<Document> documents = new ArrayList<>();
        for (Computer product: products) {
            documents.add(Document.parse(gson.toJson(product)));
        }
        collection.insertMany(documents);
    }

    @Override
    public boolean update(Computer product) {

        collection.updateOne(Filters.eq("id", product.getId()) , new Document("$set", gson.toJson(product)));
        return true;
    }

    @Override
    public boolean delete(String id) {
        collection.deleteOne(Filters.eq("id", id));
        return true;
    }

    @Override
    public List<Computer> getAll() {
        List<Computer> products = new ArrayList<>();
        return collection.find()
                .map(product -> gson.fromJson(product.toJson(),Computer.class))
                .into(products);

    }

    @Override
    public Optional<Computer> findById(String id) {
        return Optional.ofNullable(collection.find(Filters.eq("id", id))
                .map(product -> gson.fromJson(product.toJson(), Computer.class))
                .first());
    }
}

