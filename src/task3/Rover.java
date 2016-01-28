package task3;

public class Rover {
    private final Field field;
    private Position position;
    private boolean stopped = false;

    public Rover(Position position, Field field) {
        this.position = position;
        this.field = field;
    }

    public Position getPosition() {
        return position;
    }

    public void receiveSingleCommand(Command command) {
        if (stopped) {
            return;
        }
        Position newPosition = command.apply(position);
        changePosition(newPosition);

    }

    public void receiveCommands(Command... commands) {

        for (Command command : commands) {
            receiveSingleCommand(command);
        }
    }

    public String getPositionInfo() {
        if (stopped) {
            return "NOK";
        }
        return position.toString();
    }

    public Field getField() {
        return field;
    }

    private void changePosition(Position newPosition) {
        if (!onField(newPosition.getLocation())) {
            newPosition.fixPositionOnField(field.getMaxX(), field.getMaxY());
        }
        if (!obstacleFound(newPosition)) {
            position = newPosition;
        } else {
            stopped = true;
        }
    }

    private boolean obstacleFound(Position newPosition) {
        for (Obstacle obstacle : field.getObstaclesValue()) {
            if (obstacle.getCoordinates().equals(newPosition.getLocation())) {
                return true;
            }
        }
        return false;
    }


    private boolean onField(Point location) {
        return location.getX() < field.getMaxX()
                && location.getY() < field.getMaxY()
                && location.getX() >= 0
                && location.getY() >= 0;
    }
}
