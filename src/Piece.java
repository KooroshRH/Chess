import java.util.ArrayList;

abstract class Piece {
    protected int ID;
    protected int width;
    protected int height;
    protected boolean isIn;
    protected char color;
    protected static ArrayList<Piece> pieces = new ArrayList<>();

    public Piece(int width, int height, boolean isIn, char color, int ID) {
        this.width = width;
        this.height = height;
        this.isIn = isIn;
        this.color = color;
        this.ID = ID;
        pieces.add(this);
    }

    public char getColor() {
        return color;
    }

    public static ArrayList<Piece> getPieces() {
        return pieces;
    }

    public int getID() {
        return ID;
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

    private boolean correctMove(int width, int height, char[][] map){
        map[this.height][this.width] = '0';
        map[height][width] = color;
        this.height = height;
        this.width = width;
        return true;
    }

    public boolean isMoving(int width, int height, char[][] map){
        if (map[height][width] == '0'){
            return correctMove(width, height, map);
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
            return correctMove(width, height, map);
        }
    }

    abstract boolean move(int width, int height, char[][] map);

}
