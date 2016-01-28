package task3;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static task3.Command.*;

public class RoverSpec {
    private final Direction direction = Direction.NORTH;
    private final int maxXLocation = 9;
    private final int maxYLocation = 9;
    private Rover rover;
    private Position roverPosition;
    private Point location;
    private Set<Obstacle> obstacles = new HashSet<>();

    @Before
    public void beforeRoverTest() {

        location = new Point(1, 2);
        roverPosition = new Position(location, direction);
        rover = new Rover(roverPosition, new Field(maxXLocation, maxYLocation, obstacles));
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        assertEquals(roverPosition, rover.getPosition());
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expected = location.getY() + 1;
        rover.receiveSingleCommand(F);
        assertEquals(expected, rover.getPosition().getLocation().getY());
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expected = location.getY() - 1;
        rover.receiveSingleCommand(B);
        assertEquals(expected, rover.getPosition().getLocation().getY());
    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws Exception {
        rover.receiveSingleCommand(L);
        assertEquals(Direction.WEST, rover.getPosition().getDirection());
    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws Exception {
        rover.receiveSingleCommand(R);
        assertEquals(Direction.EAST, rover.getPosition().getDirection());
    }

    @Test
    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws Exception {
        int expected = location.getX() + 1;
        rover.receiveCommands(R, F, R);
        assertEquals(expected, rover.getPosition().getLocation().getX());
        assertEquals(Direction.SOUTH, rover.getPosition().getDirection());
    }

    @Test
    public void receiveCommandShouldWhatFromOneEdgeOfTheGridToAnother() throws Exception {
        int expected = maxXLocation + location.getX() - 2;
        rover.receiveCommands(L, F, F, F);
        assertEquals(expected, rover.getPosition().getLocation().getX());
    }

    @Test
    public void receiveCommandsShouldStopWhenObstacleIsFound() throws Exception {
        int expected = location.getX() + 1;
        rover.getField().addObstacle(new Obstacle(new Point(expected + 1, location.getY())));
        rover.getPosition().setDirection(Direction.EAST);
        rover.receiveCommands(F, F, F, R, F);
        assertEquals(expected, rover.getPosition().getLocation().getX());
        assertEquals(Direction.EAST, rover.getPosition().getDirection());
    }

    @Test
    public void positionShouldReturnXYAndDirection() throws Exception {
        rover.receiveCommands(L, F, F, F, R, F, F);
        assertEquals("8 X 4 N", rover.getPositionInfo());
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        rover.getField().addObstacle(new Obstacle(new Point(location.getX() + 1, location.getY())));
        rover.getPosition().setDirection(Direction.EAST);
        rover.receiveCommands(F);
        assertEquals("NOK", rover.getPositionInfo());
    }
}