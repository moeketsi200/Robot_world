package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class SimpleMaze extends AbstractMaze {
    public SimpleMaze() {
        // Places a single obstacle in the middle of the world
        this.obstacles.add(new SquareObstacle(1, 1));
    }
}