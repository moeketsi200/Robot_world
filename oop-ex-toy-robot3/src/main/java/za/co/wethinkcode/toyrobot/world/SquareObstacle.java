package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {
    private final int x;
    private final int y;

    public SquareObstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getBottomLeftX() { return x; }

    @Override
    public int getBottomLeftY() { return y; }

    @Override
    public int getSize() { return 5; }

    @Override
    public boolean blocksPosition(Position position) {
        return position.getX() >= x && position.getX() <= x + 4 &&
               position.getY() >= y && position.getY() <= y + 4;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        int minX = Math.min(a.getX(), b.getX());
        int maxX = Math.max(a.getX(), b.getX());
        int minY = Math.min(a.getY(), b.getY());
        int maxY = Math.max(a.getY(), b.getY());

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (blocksPosition(new Position(i, j))) return true;
            }
        }
        return false;
    }
}