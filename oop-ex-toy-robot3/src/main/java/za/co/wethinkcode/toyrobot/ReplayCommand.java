package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplayCommand extends Command {
    private boolean reversed = false;
    private int nCount = -1; // Represents the 'n' in 'replay n' or 'replay n-m'
    private int mCount = -1; // Represents the 'm' in 'replay n-m'

    public ReplayCommand() {
        super("replay");
    }

    public ReplayCommand(String argument) {
        super("replay", argument);
        parseArguments(argument);
    }

    private void parseArguments(String argument) {
        String arg = argument.toLowerCase().trim();
        
        if (arg.contains("reversed")) {
            this.reversed = true;
            // Clean the argument string to process numbers if they exist
            arg = arg.replace("reversed", "").trim();
        }

        if (arg.isEmpty()) return;

        if (arg.contains("-")) {
            String[] range = arg.split("-");
            try {
                this.nCount = Integer.parseInt(range[0].trim());
                this.mCount = Integer.parseInt(range[1].trim());
            } catch (NumberFormatException ignored) {}
        } else {
            try {
                this.nCount = Integer.parseInt(arg);
            } catch (NumberFormatException ignored) {}
        }
    }

    @Override
    public boolean execute(Robot target) {
        List<Command> history = target.getHistory();
        int historySize = history.size();
        int startIndex;
        int endIndex;

        // 1. Calculate Indices
        if (nCount == -1) {
            // Case: "replay" or "replay reversed" (Full History)
            startIndex = 0;
            endIndex = historySize;
        } else if (mCount == -1) {
            // Case: "replay n" (Last n commands)
            startIndex = Math.max(0, historySize - nCount);
            endIndex = historySize;
        } else {
            // Case: "replay n-m" (n-th last down to m-th last, m exclusive)
            // Example: 5-2 starts at historySize-5 and ends at historySize-2
            startIndex = Math.max(0, historySize - nCount);
            endIndex = Math.max(0, historySize - mCount);
        }

        // 2. Fetch the subset (safeguard against start > end)
        List<Command> commandsToReplay = new ArrayList<>();
        if (startIndex < endIndex && startIndex < historySize) {
            commandsToReplay.addAll(history.subList(startIndex, endIndex));
        }

        // 3. Handle reversal
        if (this.reversed) {
            Collections.reverse(commandsToReplay);
        }

        // 4. Execution Loop
        int replayedCount = 0;
        for (Command command : commandsToReplay) {
            command.execute(target);
            // Print the robot's status after every replayed move
            System.out.println(target.toString());
            replayedCount++;
        }

        target.setStatus("replayed " + replayedCount + " commands.");
        return true;
    }
}