package task3;

public class Position {
    private Point location;
    private Direction direction;

    public Position(Point location,
                    Direction directionValue) {
        this.location = location;
        direction = directionValue;

    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("%d X %d %s", location.getX(), location.getY(), direction);

    }

    public void fixPositionOnField(int maxX, int maxY) {
        if (location.getX() == -1) {
            setLocation(new Point(maxX, location.getY()));
        } else if (location.getX() == maxX + 1) {
            setLocation(new Point(0, location.getY()));
        } else {
            if (location.getY() == -1)
                setLocation(new Point(location.getX(), maxY));
            else if (location.getY() == maxY + 1)
                setLocation(new Point(location.getX(), 0));
        }

    }
}