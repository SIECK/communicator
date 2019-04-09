package com.sieck.communicator.domain;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Picture extends AbstractMongoConfiguration {

    @Value("${jsa.mongo.address}")
    private String mongoAddress;

    @Value("${jsa.mongo.database}")
    private String mongoDatabase;

    public MongoClient mongoClient(){
        return new MongoClient(mongoAddress);
    }

    public String getDatabaseName(){
        return mongoDatabase;
    }
}
