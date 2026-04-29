package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command {
    public RightCommand() {
        super("right");
    }

    @Override
    public boolean execute(Robot target) {
        target.updateDirection(true); // Assuming your robot has a method to turn
        target.setStatus("Turned right.");
        return true;
    }
}
