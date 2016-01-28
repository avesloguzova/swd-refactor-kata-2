package task3;

public enum Command {
    F {
        @Override
        Position apply(Position position) {
            Point newLocation = position.getDirection().moveForward(position.getLocation());
            return new Position(newLocation, position.getDirection());
        }
    },
    B {
        @Override
        Position apply(Position position) {
            Point newLocation = position.getDirection().moveBack(position.getLocation());
            return new Position(newLocation, position.getDirection());
        }
    },
    L {
        @Override
        Position apply(Position position) {
            Direction newDirection = position.getDirection().turnLeft();
            return new Position(position.getLocation(), newDirection);
        }
    },
    R {
        @Override
        Position apply(Position position) {
            Direction newDirection = position.getDirection().turnRight();
            return new Position(position.getLocation(), newDirection);
        }
    };

    abstract Position apply(Position position);

}
