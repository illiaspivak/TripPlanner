package sk.kosickaakademia.spivak.tripplanner.util;

import org.json.simple.JSONObject;
import sk.kosickaakademia.spivak.tripplanner.collections.Trip;

public class Util {

    /**
     * Create Json
     * @param trip
     * @return JSONObject
     */
    public JSONObject getJson (Trip trip){
        if (trip==null){
            return null;
        }
        JSONObject object = new JSONObject();

        object.put("title",trip.getTitle());
        object.put("place",trip.getPlace());
        object.put("distance",trip.getApproximateDistance());
        object.put("placeVisited",trip.isPlaceVisited());
        object.put("difficultyLevel",trip.getDifficultyLevel());

        return object;
    }
}
