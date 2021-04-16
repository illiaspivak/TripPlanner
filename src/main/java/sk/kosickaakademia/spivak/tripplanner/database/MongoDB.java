package sk.kosickaakademia.spivak.tripplanner.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sk.kosickaakademia.spivak.tripplanner.log.Log;

public class MongoDB {
    Log log = new Log();
    private static MongoClient client = MongoClients.create();

    /**
     * Testing the database connection
     */
    public void test() {
        MongoDatabase database = client.getDatabase("testMongo");
        MongoCollection<Document> collection = database.getCollection("collection");
        log.okay("There is a connection to the database");
        Document document = new Document("name", "test").append("number",1);
        collection.insertOne(document);
    }
}
