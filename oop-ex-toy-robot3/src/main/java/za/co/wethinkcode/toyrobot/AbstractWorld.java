package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld implements IWorld {
    private final Position TOP_LEFT = new Position(-200, 200);
    private final Position BOTTOM_RIGHT = new Position(200, -200);
    
    private Position position;
    private IWorld.Direction currentDirection;
    private final Maze maze;

    public AbstractWorld(Maze maze) {
        this.position = CENTRE;
        this.currentDirection = IWorld.Direction.UP;
        this.maze = maze;
    }
    
    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int stepX = 0;
        int stepY = 0;

        if (IWorld.Direction.UP.equals(this.currentDirection)) {
            stepY = 1;
        } else if (IWorld.Direction.DOWN.equals(this.currentDirection)) {
            stepY = -1;
        } else if (IWorld.Direction.RIGHT.equals(this.currentDirection)) {
            stepX = 1;
        } else if (IWorld.Direction.LEFT.equals(this.currentDirection)) {
            stepX = -1;
        }

        Position finalPosition = new Position(this.position.getX() + (stepX * nrSteps), this.position.getY() + (stepY * nrSteps));
        if (!isNewPositionAllowed(finalPosition)) {
            return UpdateResponse.FAILED_OUTSIDE_WORLD;
        }

        if (maze != null && maze.blocksPath(this.position, finalPosition)) {
            // Blocked! Return where we bumped into it without moving entirely
            for (Obstacle obs : maze.getObstacles()) {
                if (obs.blocksPath(this.position, finalPosition)) {
                    if (obs instanceof za.co.wethinkcode.toyrobot.world.Mountain) {
                        return UpdateResponse.FAILED_MOUNTAIN;
                    } else if (obs instanceof za.co.wethinkcode.toyrobot.world.Lake) {
                        return UpdateResponse.FAILED_LAKE;
                    }
                }
            }
            return UpdateResponse.FAILED_OBSTRUCTED;
        }

        this.position = finalPosition;
        return UpdateResponse.SUCCESS;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        int currentOrdinal = this.currentDirection.ordinal();
        if (turnRight) {
            this.currentDirection = IWorld.Direction.values()[(currentOrdinal + 1) % 4];
        } else {
            this.currentDirection = IWorld.Direction.values()[(currentOrdinal + 3) % 4];
        }
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public IWorld.Direction getCurrentDirection() {
        return this.currentDirection;
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT, BOTTOM_RIGHT);
    }

    @Override
    public boolean isAtEdge() {
        return this.position.getX() == TOP_LEFT.getX() || this.position.getX() == BOTTOM_RIGHT.getX() ||
               this.position.getY() == TOP_LEFT.getY() || this.position.getY() == BOTTOM_RIGHT.getY();
    }

    @Override
    public void reset() {
        this.position = CENTRE;
        this.currentDirection = IWorld.Direction.UP;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return maze != null ? maze.getObstacles() : new ArrayList<>();
    }

    @Override
    public void showObstacles() {
        // To be implemented by specific worlds like TextWorld
    }
}
