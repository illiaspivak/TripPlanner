package sk.kosickaakademia.spivak.tripplanner;

import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.database.MongoDB;
import sk.kosickaakademia.spivak.tripplanner.util.Util;

public class Main
{
    public static void main( String[] args )
    {
        TestGetAllTripsJson();
        TestDeleteTrip();
        TestGetAllTrips();
    }

    public static void TestGetAllTrips(){
        MongoDB mongoDB = new MongoDB();
        System.out.println(mongoDB.getAllTrips());
    }

    public static void TestGetAllTripsJson(){
        MongoDB mongoDB = new MongoDB();
        System.out.println(mongoDB.getAllTripsJson());
    }

    public static void TestInsertTripJSON(){
        MongoDB mongoDB = new MongoDB();
        Util util = new Util();
        String title = "Velky Sokol";
        String place = "Slovensky raj";
        int distance = 35;
        int difficultyLevel = 3;
        Trip trip = new Trip(title,place,distance,false,difficultyLevel);
        mongoDB.insertTripJSON(util.getJson(trip));
    }

    public static void TestDeleteTrip(){
        MongoDB mongoDB = new MongoDB();
        mongoDB.deleteTrip("Vodopád Skok");
    }

    public static void TestInsertTrip(){
        MongoDB mongoDB = new MongoDB();
        String title = "Vodopád Skok";
        String place = "Vysoke Tatry";
        int distance = 10;
        int difficultyLevel = 1;
        Trip trip = new Trip(title,place,distance,false,difficultyLevel);
        mongoDB.insertTrip(trip);
    }

    public static void TestUpdatePlaceVisited(){
        MongoDB mongoDB = new MongoDB();
        mongoDB.setPlaceVisited("Ďumbier");
    }

}
