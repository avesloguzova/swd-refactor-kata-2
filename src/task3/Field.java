package task3;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by av on 28/01/16.
 */
public class Field {
    private final int maxXLocation;

    private final int maxYLocation;

    private final Set<Obstacle> obstaclesValue;

    public Field(int maxXLocation, int maxYLocation, Set<Obstacle> obstaclesValue) {
        this.maxXLocation = maxXLocation;
        this.maxYLocation = maxYLocation;
        this.obstaclesValue = new HashSet<>(obstaclesValue);
    }

    public int getMaxX() {
        return maxXLocation;
    }

    public int getMaxY() {
        return maxYLocation;
    }

    public Set<Obstacle> getObstaclesValue() {
        return obstaclesValue;
    }

    public void addObstacle(Obstacle obstacle) {
        obstaclesValue.add(obstacle);
    }

    public boolean isValidLocation(Point location) {
        for (Obstacle obs : obstaclesValue) {
            if (obs.getCoordinates().equals(location)) {
                return false;
            }

        }
        return true;
    }
}
