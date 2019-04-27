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

    public boolean isMoving(int width, int height, char[][] map){
        if (map[height][width] == '0'){
            map[this.height][this.width] = '0';
            map[height][width] = color;
            return true;
        } else if (map[height][width] != '0' && map[height][width] == color){
            System.out.println("This place is filled by your own piece!!");
            return false;
        } else {
            for (Piece piece : pieces) {
                if (piece.getHeight() == height && piece.getWidth() == width) {
                    piece.setIn(false);
                }
            }
            System.out.println("You hit the enemy piece!!");
            map[this.height][this.width] = '0';
            map[height][width] = color;
            return true;
        }
    }

    abstract boolean move(int width, int height, char[][] map);

}
