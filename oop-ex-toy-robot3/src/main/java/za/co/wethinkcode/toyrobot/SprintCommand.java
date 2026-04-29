package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class SprintCommand extends Command {
    public SprintCommand(String argument) {
        super("sprint", argument);
    }

    @Override
    public boolean execute(Robot target) {
        int steps = Integer.parseInt(getArgument());
        return sprintRecursively(target, steps);
    }

    private boolean sprintRecursively(Robot target, int steps) {
        if (steps <= 0) return true;

        IWorld.UpdateResponse response = target.updatePosition(steps);
        if (response == IWorld.UpdateResponse.SUCCESS) {
            target.setStatus("Moved forward by " + steps + " steps.");
            
            if (steps > 1) {
                System.out.println(target.toString());
            }

            // Recurse with one fewer step
            return sprintRecursively(target, steps - 1);
        } else if (response == IWorld.UpdateResponse.FAILED_OBSTRUCTED) {
            target.setStatus("Sorry, there is an obstacle in the way.");
            return false;
        } else if (response == IWorld.UpdateResponse.FAILED_MOUNTAIN) {
            target.setStatus("Sorry, there is a mountain in the way.");
            return false;
        } else if (response == IWorld.UpdateResponse.FAILED_LAKE) {
            target.setStatus("Sorry, there is a lake in the way.");
            return false;
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
            return false;
        }
    }
}
