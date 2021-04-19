package sk.kosickaakademia.spivak.tripplanner;

import sk.kosickaakademia.spivak.tripplanner.collections.Trip;
import sk.kosickaakademia.spivak.tripplanner.database.MongoDB;

public class Main
{
    public static void main( String[] args )
    {
        MongoDB mongoDB = new MongoDB();
        Trip trip = new Trip("Ďumbier", "Nízké Tatry", 30,false, 3);
        mongoDB.insertTrip(trip);
    }
}
