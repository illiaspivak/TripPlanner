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
        System.out.println(mongoDB.getAllTrips());
    }

}
