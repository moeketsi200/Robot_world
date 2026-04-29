package za.co.wethinkcode.toyrobot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.Lake;
import za.co.wethinkcode.toyrobot.world.Mountain;

import java.util.List;

public class RobotWindow extends Application {
    private static List<Obstacle> obstacles;
    private static Polygon robotShape;

    // Allows us to pass the obstacles to the JavaFX Application before launching
    public static void setObstacles(List<Obstacle> obs) {
        obstacles = obs;
    }

    // Updates the robot's position and rotation on the JavaFX thread
    public static void updateRobot(Position pos, IWorld.Direction dir) {
        if (robotShape == null) return;

        Platform.runLater(() -> {
            // Map game coordinates [-200, 200] to window coordinates [0, 400]
            robotShape.setLayoutX(pos.getX() + 200);
            robotShape.setLayoutY(200 - pos.getY()); // Y is inverted in JavaFX

            // Rotate the shape based on direction
            switch (dir) {
                case UP: robotShape.setRotate(0); break;
                case RIGHT: robotShape.setRotate(90); break;
                case DOWN: robotShape.setRotate(180); break;
                case LEFT: robotShape.setRotate(270); break;
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        if (obstacles != null) {
            for (Obstacle obs : obstacles) {
                int size = obs.getSize();
                // Shift coordinates from [-200, 200] to [0, 400] for JavaFX rendering
                int x = obs.getBottomLeftX() + 200;
                int y = 200 - (obs.getBottomLeftY() + size); // JavaFX Y-axis goes downwards
                
                if (obs instanceof Lake) {
                    double radius = size / 2.0;
                    Circle circle = new Circle(x + radius, y + radius, radius);
                    circle.setFill(Color.BLUE); // Lakes are circular and blue
                    root.getChildren().add(circle);
                } else if (obs instanceof Mountain) {
                    Rectangle rect = new Rectangle(x, y, size, size);
                    rect.setFill(Color.DARKGRAY); // Mountains are grey
                    root.getChildren().add(rect);
                } else {
                    Rectangle rect = new Rectangle(x, y, size, size);
                    rect.setFill(Color.DARKRED); // Regular obstacles are red
                    root.getChildren().add(rect);
                }
            }
        }

        // Create a green triangle to represent the robot
        robotShape = new Polygon();
        robotShape.getPoints().addAll(new Double[]{
                0.0, -10.0,  // Top point
                -7.0, 7.0,   // Bottom left point
                7.0, 7.0     // Bottom right point
        });
        robotShape.setFill(Color.GREEN);
        robotShape.setLayoutX(200); // Start at center (0,0 in game coords)
        robotShape.setLayoutY(200);
        root.getChildren().add(robotShape);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Toy Robot World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}