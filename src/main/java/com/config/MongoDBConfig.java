package com.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConfig {
    private static final String URL = "localhost";
    private static final String databaseName = "Nix";
    private static final int port = 27017;

    private static MongoDatabase mongoDatabase;
    private static MongoClient mongoClient;

    public static MongoDatabase getMongoDatabase(){
        if (mongoClient == null){
            mongoClient = new MongoClient(URL, port);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            mongoDatabase.drop();
        }
        return mongoDatabase;
    }

}
