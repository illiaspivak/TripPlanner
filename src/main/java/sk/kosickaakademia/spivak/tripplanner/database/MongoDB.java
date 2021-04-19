package sk.kosickaakademia.spivak.tripplanner.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.log.Log;

public class MongoDB {
    Log log = new Log();
    private static MongoClient client = MongoClients.create();


    /**
     * Adding a trip to the database
     * @param trip
     */
    public void insertTrip(Trip trip) {
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            Document document = new Document("title",trip.getTitle()).append("place",trip.getPlace()).append("distance", trip.getApproximateDistance()).append("placeVisited",trip.isPlaceVisited()).append("difficultyLevel",trip.getDifficultyLevel());
            collection.insertOne(document);
            log.okay("Trip added");
        }catch (Exception e){
            log.error(e.toString());
        }

    }

}
