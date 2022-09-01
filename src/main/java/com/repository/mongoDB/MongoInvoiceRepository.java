package com.repository.mongoDB;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.config.MongoDBConfig;
import com.google.gson.*;
import com.model.Invoice;
import com.model.Product;
import com.model.computer.Computer;
import com.model.phone.Phone;
import com.model.television.Television;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.repository.InvoiceRepository;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@Singleton
public class MongoInvoiceRepository implements InvoiceRepository {
    private static MongoInvoiceRepository instance;
    private static final MongoDatabase mongoDatabase = MongoDBConfig.getMongoDatabase();
    private final MongoCollection<Document> collection;
    private final MongoCollection<Document> collectionPhones;
    private final MongoCollection<Document> collectionComputer;
    private final MongoCollection<Document> collectionTelevision;
    private final Gson gson;



    public MongoInvoiceRepository() {
        collection = mongoDatabase.getCollection(Invoice.class.getSimpleName());
        collectionPhones = mongoDatabase.getCollection(Phone.class.getSimpleName());
        collectionComputer = mongoDatabase.getCollection(Computer.class.getSimpleName());
        collectionTelevision = mongoDatabase.getCollection(Television.class.getSimpleName());
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsString() + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withLocale(Locale.ENGLISH)))
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    public static MongoInvoiceRepository getInstance(){
        if (instance == null){
            instance = new MongoInvoiceRepository();
        }
        return instance;
    }


    @Override
    public Invoice save(Invoice invoice) {
    collection.insertOne(Document.parse(gson.toJson(invoice)));
    return invoice;
    }

  @Override
    public List<Invoice> findInvoicesWithSumHigher(Double sum) {
        List<Invoice> invoices = new ArrayList<>();
        collection.find(Filters.gt("sum", sum))
                .map(document -> gson.fromJson(document.toJson(), Invoice.class))
                .into(invoices);
        return invoices;
    }


    @Override
    public Long countOfInvoices() {
       return collection.countDocuments();
    }

    @Override
    public void update(Invoice invoice) {
        collection.updateOne(Filters.eq("id", invoice.getId()),new Document("$set", Document.parse(gson.toJson(invoice))));
    }

    @Override
    public List<String> groupInvoicesBySum() {
        List<String> result = new ArrayList<>();
        Bson filter = Aggregates.group("$sum", Accumulators.sum("count", 1));
        collection.aggregate(List.of(filter))
                .map(document -> gson.fromJson(document.toJson(), JsonObject.class))
                .forEach((Consumer<? super JsonObject>) jsonObject -> {
                    result.add("count =  " + jsonObject.get("count").getAsInt() + " sum = " + jsonObject.get("_id").getAsDouble());
                });
        return result;
    }
}
