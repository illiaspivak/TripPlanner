package sk.kosickaakademia.spivak.tripplanner.collections;

public class Trip {
    private String title;
    private String place;
    private int approximateDistance;
    private boolean placeVisited;
    private int difficultyLevel;

    public Trip(String title, String place, int approximateDistance, boolean placeVisited, int difficultyLevel) {
        this.title = title;
        this.place = place;
        this.approximateDistance = approximateDistance;
        this.placeVisited = placeVisited;
        this.difficultyLevel = difficultyLevel;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public int getApproximateDistance() {
        return approximateDistance;
    }

    public boolean isPlaceVisited() {
        return placeVisited;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
