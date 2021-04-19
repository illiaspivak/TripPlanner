package sk.kosickaakademia.spivak.tripplanner.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.log.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Creating a list of all trips from the database
     * @return List<Trip>
     */
    public List<Trip> getAllTrips(){
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            log.info("Connect to the database");
            List<Trip> list = new ArrayList<>();
            for (Document document : collection.find()){
                String title = document.getString("title");
                String place = document.getString("place");
                int distance = document.getInteger("distance");
                boolean placeVisited = document.getBoolean("placeVisited");
                int difficultyLevel = document.getInteger("difficultyLevel");
                Trip trip = new Trip(title,place,distance,placeVisited,difficultyLevel);
                list.add(trip);
                     }
            log.okay("List of trips created");
            return list;
        }catch (Exception e){
            log.error(e.toString());
        }
        return null;
    }

    /**
     * Creating a list of trips of a certain level of difficulty from the database
     * @param level
     * @return List<Trip>
     */
    public List<Trip> getAllTrips(int level){
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            log.info("Connect to the database");
            List<Trip> list = new ArrayList<>();
            for (Document document : collection.find()){
                int difficultyLevel = document.getInteger("difficultyLevel");
                if(difficultyLevel==level) {
                    String title = document.getString("title");
                    String place = document.getString("place");
                    int distance = document.getInteger("distance");
                    boolean placeVisited = document.getBoolean("placeVisited");
                    Trip trip = new Trip(title, place, distance, placeVisited, difficultyLevel);
                    list.add(trip);
                }
            }
            log.okay("List of trips created");
            return list;
        }catch (Exception e){
            log.error(e.toString());
        }
        return null;
    }

}
