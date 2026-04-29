package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.List;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class Robot {
    private String status;
    private String name;
    private List<Command> history;
    private IWorld world;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.history = new ArrayList<>();
        this.world = new TextWorld(new za.co.wethinkcode.toyrobot.maze.EmptyMaze());
    }

    public void setWorld(IWorld world) {
        this.world = world;
    }

    public String getStatus() {
        return this.status;
    }

    public Direction getCurrentDirection() {
        switch(this.world.getCurrentDirection()) {
            case UP: return Direction.NORTH;
            case RIGHT: return Direction.EAST;
            case DOWN: return Direction.SOUTH;
            case LEFT: return Direction.WEST;
            default: return Direction.NORTH;
        }
    }

    public boolean handleCommand(Command command) {
        String name = command.getName();
        if (name.equals("forward") || name.equals("back") || 
            name.equals("right") || name.equals("left") || 
            name.equals("sprint")) {
            history.add(command);
        }
        return command.execute(this);
    }

    public List<Command> getHistory() {
        return this.history;
    }

    public IWorld.UpdateResponse updatePosition(int nrSteps) {
        return this.world.updatePosition(nrSteps);
    }

    public void updateDirection(boolean turnRight) {
        this.world.updateDirection(turnRight);
    }

    @Override
    public String toString() {
       return "[" + this.world.getPosition().getX() + "," + this.world.getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.world.getPosition();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
}