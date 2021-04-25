package sk.kosickaakademia.spivak.tripplanner.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.spivak.tripplanner.database.MongoDB;

@RestController
public class Controller {

    /**
     * Method GET: Show all trips
     * @return json
     */
    @GetMapping("/trip")
    public ResponseEntity<String> getAllUsers(){
        String json = new MongoDB().getAllTripsJson();
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json);
    }
}
