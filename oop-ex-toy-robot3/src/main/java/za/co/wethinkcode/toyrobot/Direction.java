package za.co.wethinkcode.toyrobot;

// Direction.java
public enum Direction {
    NORTH, // 0
    EAST,  // 1
    SOUTH, // 2
    WEST;  // 3

    public Direction turnRight() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public Direction turnLeft() {
        // This moves 3 steps forward, which is 1 step backward (Left)
        return values()[(this.ordinal() + 3) % values().length];
    }
}