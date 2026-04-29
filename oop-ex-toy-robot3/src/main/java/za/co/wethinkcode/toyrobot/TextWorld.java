package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.world.Obstacle;

public class TextWorld extends AbstractWorld {

    public TextWorld(Maze maze) {
        super(maze);
    }

    @Override
    public void showObstacles() {
        if (getObstacles().isEmpty()) return;
        
        System.out.println("There are some obstacles:");
        for (Obstacle obs : getObstacles()) {
            int x1 = obs.getBottomLeftX();
            int y1 = obs.getBottomLeftY();
            System.out.println("- At position " + x1 + "," + y1 + " (to " + (x1 + 4) + "," + (y1 + 4) + ")");
        }
    }
}

    
