public class Soldier extends Piece {
    public Soldier(int width, int height, boolean isIn, char color) {
        super(width, height, isIn, color);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        return false;
    }
}
