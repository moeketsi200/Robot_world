package za.co.wethinkcode.toyrobot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move backward by specified number of steps, e.g. 'BACK 10'\n" +
                "RIGHT - turn right by 90 degrees\n" +
                "LEFT - turn left by 90 degrees\n" +
                "SPRINT - forward incrementally, e.g. 'SPRINT 5'\n" +
                "REPLAY - repeats previous movement commands");
        return true;
    }
}
