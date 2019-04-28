public class Horse extends Piece {

    public Horse(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        return false;
    }
}
