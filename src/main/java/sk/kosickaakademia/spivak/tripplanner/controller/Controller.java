package sk.kosickaakademia.spivak.tripplanner.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.database.MongoDB;
import sk.kosickaakademia.spivak.tripplanner.log.Log;

@RestController
public class Controller {
    Log log = new Log();

    /**
     * Method GET: Show all trips
     * @return json
     */
    @GetMapping("/trip")
    public ResponseEntity<String> getAllUsers(){
        String json = new MongoDB().getAllTripsJson();
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json);
    }

    /**
     * Method POST: Adding a new trip to the database
     * @param data (json)
     * @return
     */
    @PostMapping("/trip/new")
    public ResponseEntity<String> insertNewTrip(@RequestBody String data){
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(data);
            String title = ((String)object.get("title"));
            String place = ((String)object.get("place"));
            int distance = Integer.parseInt(String.valueOf(object.get("distance")));
            int difficultyLevel = Integer.parseInt(String.valueOf(object.get("difficultyLevel")));

            log.info("Checking if there are any input data");
            if(title==null || place==null || distance < 0 || difficultyLevel < 1 || difficultyLevel > 3){
                log.error("Incorrect input data");
                JSONObject objectError = new JSONObject();
                objectError.put("error", "Incorrect input data");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(objectError.toJSONString());
            }
            Trip trip = new Trip(title,place,distance,false,difficultyLevel);
            new MongoDB().insertTrip(trip);
            log.info("Adding a new trip to the database");
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("New trip has been added to the database");

        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            log.error("Cannot process input data ");
            obj.put("error","Cannot process input data ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
    }
}
