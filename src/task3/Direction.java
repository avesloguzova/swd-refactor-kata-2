package task3;

public enum Direction {
    NORTH {
        @Override
        Point moveForward(Point location) {
            return new Point(location.getX(), location.getY() + 1);
        }

        @Override
        Point moveBack(Point location) {
            return new Point(location.getX(), location.getY() - 1);
        }

        @Override
        Direction turnRight() {
            return EAST;
        }

        @Override
        Direction turnLeft() {
            return WEST;
        }

        @Override
        public String toString() {
            return "N";
        }
    },
    EAST {
        @Override
        Point moveForward(Point location) {
            return new Point(location.getX() + 1, location.getY());

        }

        @Override
        Point moveBack(Point location) {
            return new Point(location.getX() - 1, location.getY());
        }

        @Override
        Direction turnRight() {
            return SOUTH;
        }

        @Override
        Direction turnLeft() {
            return NORTH;
        }

        @Override
        public String toString() {
            return "E";
        }
    },
    SOUTH {
        @Override
        Point moveForward(Point location) {
            return NORTH.moveBack(location);
        }

        @Override
        Point moveBack(Point location) {
            return NORTH.moveForward(location);
        }

        @Override
        Direction turnRight() {
            return WEST;
        }

        @Override
        Direction turnLeft() {
            return EAST;
        }

        @Override
        public String toString() {
            return "S";
        }
    },
    WEST {
        @Override
        Point moveForward(Point location) {
            return EAST.moveBack(location);
        }

        @Override
        Point moveBack(Point location) {
            return EAST.moveForward(location);
        }

        @Override
        Direction turnRight() {
            return NORTH;
        }

        @Override
        Direction turnLeft() {
            return SOUTH;
        }

        @Override
        public String toString() {
            return "W";
        }
    };


    abstract Point moveForward(Point location);

    abstract Point moveBack(Point location);

    abstract Direction turnRight();

    abstract Direction turnLeft();
}