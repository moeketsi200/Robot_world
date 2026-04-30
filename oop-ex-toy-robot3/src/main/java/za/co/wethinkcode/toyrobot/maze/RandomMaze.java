package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;
import za.co.wethinkcode.toyrobot.world.Mountain;
import java.util.Random;

public class RandomMaze extends AbstractMaze {
    public RandomMaze() {
        Random rand = new Random();
        int numObstacles = rand.nextInt(10) + 1; // 1 to 10 obstacles
        
        for (int i = 0; i < numObstacles; i++) {
            // Generate random bounds within the 200x200 world
            int x = rand.nextInt(200) - 100;
            int y = rand.nextInt(200) - 100;
            int obstacleType = rand.nextInt(2); // 0 or 1
            if (obstacleType == 0) {
                this.obstacles.add(new Mountain(x, y));
            } else {
                this.obstacles.add(new SquareObstacle(x, y));
            }
        }
    }
}