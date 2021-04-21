package sk.kosickaakademia.spivak.tripplanner.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.log.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MongoDB {
    Log log = new Log();
    private static MongoClient client = MongoClients.create();

    /**
     * Adding a trip to the database
     * @param trip
     */
    public void insertTrip(Trip trip) {
        if(trip == null){
            log.error("No input data");
            return;
        }
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
        if (level < 1 || level > 3){
            log.error("Invalid input data");
            return null;
        }
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

    /**
     * Creating a list of trips of a certain place from the database
     * @param location
     * @return List<Trip>
     */
    public List<Trip> getAllTrips(String location){
        if(location == null){
            log.error("No input data");
            return null;
        }
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            log.info("Connect to the database");
            List<Trip> list = new ArrayList<>();
            for (Document document : collection.find()){
                String place = document.getString("place");
                if(place.equals(location)) {
                    String title = document.getString("title");
                    int distance = document.getInteger("distance");
                    boolean placeVisited = document.getBoolean("placeVisited");
                    int difficultyLevel = document.getInteger("difficultyLevel");
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

    /**
     * Changing the placeVisited parameter from "false" to "true"
     * @param name of the trip
     */
    public boolean setPlaceVisited(String name) {
        if(name == null){
            log.error("No input data");
            return false;
        }
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            log.info("Connect to the database");
            for (Document document : collection.find()){
                String title = document.getString("title");
                if(title.equals(name)) {
                    BasicDBObject searchQuery = new BasicDBObject();
                    searchQuery.append("title", name);

                    BasicDBObject updateQuery = new BasicDBObject();
                    updateQuery.append("$set", new BasicDBObject().append("placeVisited", true));

                    database.getCollection("trips").updateMany(searchQuery, updateQuery);
                    log.info("Changed the value from \"false\" to \" true\"");
                    return true;
                }
            }
            log.error("This title is not in the database");
            return false;
        }catch (Exception e){
            log.error(e.toString());
        }
        return false;
    }

    /**
     * Created Json all trips
     * @return Json
     */
    public String getAllTripsJson() {
        try {
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");

            JSONArray jsonArray = new JSONArray();

            for (Document doc : collection.find()) {
                try {
                    JSONObject tripJson = (JSONObject) new JSONParser().parse(doc.toJson());
                    jsonArray.add(tripJson);

                } catch (org.json.simple.parser.ParseException e) {
                    log.error(e.toString());
                }
            }
            log.okay("Json file created");
            return jsonArray.toJSONString();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;
    }

    /**
     * Adding a Json to the database
     * @param trip
     */
    public void insertTripJSON(JSONObject trip) {
        if(trip == null){
            log.error("No input data");
            return;
        }
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            Document document = new Document(trip);
            collection.insertOne(document);
            log.okay("Trip added");
        }catch (Exception e){
            log.error(e.toString());
        }
    }

    /**
     * Delete trip from database
     * @param name
     */
    public void deleteTrip(String name){
        if(name == null){
            log.error("No input data");
            return;
        }
        try{
            MongoDatabase database = client.getDatabase("tripplanner");
            MongoCollection<Document> collection = database.getCollection("trips");
            collection.deleteOne(Filters.eq("title", name));
            log.okay("Trip deleted");
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
