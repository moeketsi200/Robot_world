package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                         
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        String commandLine = args[0];
        String argument = args.length >1 ? args[1]: "";
        switch (commandLine){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(argument);
            case "back":
                return new BackCommand(argument);
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();
            case "sprint":
                return new SprintCommand(argument);
            case "replay":
                if (args.length > 1) {
                    return new ReplayCommand(instruction.substring(instruction.indexOf(' ')).trim());
                }
                return new ReplayCommand();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
