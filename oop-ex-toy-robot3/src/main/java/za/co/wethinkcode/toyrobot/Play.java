package za.co.wethinkcode.toyrobot;

import java.util.Scanner;
import za.co.wethinkcode.toyrobot.maze.*;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Robot robot;

        // args[0] is the world type (text or gui)
        String worldType = args.length > 0 ? args[0].toLowerCase() : "text";
        String mazeName = args.length > 1 ? args[1] : "RandomMaze";
        Maze maze;
        switch (mazeName) {
            case "EmptyMaze": maze = new EmptyMaze(); break;
            case "SimpleMaze": maze = new SimpleMaze(); break;
            case "RandomMaze": maze = new RandomMaze(); break;
            default: maze = new RandomMaze(); mazeName = "RandomMaze"; break;
        }

        IWorld world;
        if (worldType.equals("gui")) {
            world = new GUIWorld(maze);
        } else {
            world = new TextWorld(maze);
        }

        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        robot.setWorld(world);

        System.out.println("Hello Kiddo!");
        System.out.println("Loaded " + mazeName + ".");
        world.showObstacles();

        System.out.println(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);

    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
