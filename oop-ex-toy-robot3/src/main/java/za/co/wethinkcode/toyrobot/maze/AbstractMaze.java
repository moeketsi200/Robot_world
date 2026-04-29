package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaze implements Maze {
    protected List<Obstacle> obstacles = new ArrayList<>();

    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obs : obstacles) {
            if (obs.blocksPath(a, b)) return true;
        }
        return false;
    }
}