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
        int pX = position.getX();
        int pY = position.getY();
        return pX >= this.x && pX < (this.x + this.size) &&
               pY >= this.y && pY < (this.y + this.size);
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        if (a.getX() == b.getX()) { // Vertical movement
            int startY = Math.min(a.getY(), b.getY());
            int endY = Math.max(a.getY(), b.getY());
            for (int currentY = startY; currentY <= endY; currentY++) {
                if (blocksPosition(new Position(a.getX(), currentY))) {
                    return true;
                }
            }
        } else if (a.getY() == b.getY()) { // Horizontal movement
            int startX = Math.min(a.getX(), b.getX());
            int endX = Math.max(a.getX(), b.getX());
            for (int currentX = startX; currentX <= endX; currentX++) {
                if (blocksPosition(new Position(currentX, a.getY()))) {
                    return true;
                }
            }
        }
        return false;
    }
}