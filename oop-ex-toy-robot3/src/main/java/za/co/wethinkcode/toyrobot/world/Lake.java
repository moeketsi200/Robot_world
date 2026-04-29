package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class Lake implements Obstacle {
    private final int x;
    private final int y;
    private final int size;

    public Lake(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 5; // A 5x5 lake
    }

    @Override
    public int getBottomLeftX() { return x; }

    @Override
    public int getBottomLeftY() { return y; }

    @Override
    public int getSize() { return size; }

    @Override
    public boolean blocksPosition(Position position) {
        return position.getX() >= x && position.getX() <= x + size &&
               position.getY() >= y && position.getY() <= y + size;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        // Simple check: if it blocks the destination
        // (A fully robust check would verify the entire line between a and b)
        return blocksPosition(b);
    }
}