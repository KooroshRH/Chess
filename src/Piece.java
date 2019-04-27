import java.util.ArrayList;

abstract class Piece {
    protected int width;
    protected int height;
    protected boolean isIn;
    protected char color;
    protected static ArrayList<Piece> pieces = new ArrayList<>();

    public Piece(int width, int height, boolean isIn, char color) {
        this.width = width;
        this.height = height;
        this.isIn = isIn;
        this.color = color;
        pieces.add(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    abstract boolean move(int width, int height, char[][] map);

}
