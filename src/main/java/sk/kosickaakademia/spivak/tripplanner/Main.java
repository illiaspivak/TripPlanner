package sk.kosickaakademia.spivak.tripplanner;

import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.database.MongoDB;

public class Main
{
    public static void main( String[] args )
    {
        TestGetAllTrips();
    }

    public static void TestGetAllTrips(){
        MongoDB mongoDB = new MongoDB();
        System.out.println(mongoDB.getAllTrips("Vysoke Tatry"));
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

}
