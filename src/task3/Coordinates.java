package task3;

import java.util.List;

public class Coordinates {
  private final Point xValue;
  private final Point yValue;
  private Direction direction;
  private final List<Obstacle> obstaclesValue;
  private List<Obstacle> Obstacles;

  public Point getX() {
    return xValue;
  }

  public Point getY() {
    return yValue;
  }

  public void setDirection(Direction value) {
    direction = value;
  }
  public Direction getDirection() {
    throw new UnsupportedOperationException();
  }

  public void setObstacles(List<Obstacle> value) {
    Obstacles = value;
  }

  public Coordinates(Point xValue,
                     Point yValue,
                     Direction directionValue,
                     List<Obstacle> obstaclesValue) {
    this.xValue = xValue;
    this.yValue = yValue;
    direction = directionValue;
    this.obstaclesValue = obstaclesValue;
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException();
  }

}