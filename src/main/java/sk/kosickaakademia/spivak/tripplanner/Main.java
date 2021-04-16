package sk.kosickaakademia.spivak.tripplanner;

import sk.kosickaakademia.spivak.tripplanner.database.MongoDB;

public class Main
{
    public static void main( String[] args )
    {
        MongoDB mongoDB = new MongoDB();
        mongoDB.test();
    }
}
