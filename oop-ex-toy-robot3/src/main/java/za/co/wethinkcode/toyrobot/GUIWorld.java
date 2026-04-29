package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class GUIWorld extends AbstractWorld {
    public GUIWorld(Maze maze) {
        super(maze);
    }

    @Override
    public void showObstacles() {
        System.out.println("GUI Mode active! Launching window...");
        RobotWindow.setObstacles(getObstacles());
        new Thread(() -> javafx.application.Application.launch(RobotWindow.class)).start();
    }

    @Override
    public IWorld.UpdateResponse updatePosition(int nrSteps) {
        IWorld.UpdateResponse response = super.updatePosition(nrSteps);
        // Notify the UI to move the robot
        RobotWindow.updateRobot(getPosition(), getCurrentDirection());
        return response;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        super.updateDirection(turnRight);
        // Notify the UI to rotate the robot
        RobotWindow.updateRobot(getPosition(), getCurrentDirection());
    }
}