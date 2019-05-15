import javax.swing.JButton;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * A general class for chess pieces that extends JButton class
 *
 * @author Korosh Roohi
 * @version 1.0.0
 * @since 2019.5.13
 */
abstract class Piece extends JButton {
    protected int ID;
    protected int myWidth;
    protected int myHeight;
    protected boolean isIn;
    protected char color;
    protected static ArrayList<Piece> pieces = new ArrayList<>();

    public Piece(int myWidth, int myHeight, boolean isIn, char color, int ID) {
        super();
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.isIn = isIn;
        this.color = color;
        this.ID = ID;
        setPreferredSize(new Dimension(100, 100));
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

    public int getMyWidth() {
        return myWidth;
    }

    public int getMyHeight() {
        return myHeight;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    /**
     * A method for swap a piece in map
     *
     * @param width myWidth of piece
     * @param height myHeight of piece
     * @param map map that pieces are there
     * @return a boolean that indicate if the piece moved or not
     */
    private boolean correctMove(int width, int height, char[][] map){
        map[this.myHeight -1][this.myWidth -1] = '0';
        map[height-1][width-1] = color;
        this.myHeight = height;
        this.myWidth = width;
        return true;
    }

    /**
     * This method check the way that the specific piece can move
     *
     * @param width myWidth of piece
     * @param height myHeight of piece
     * @param map map that pieces are there
     * @param print a boolean for printing or not
     * @return a boolean that indicate if the piece moved or not
     */
    public boolean wayKeeper(int width, int height, char[][] map, boolean print){
        int widthRatio = Integer.compare(width, this.myWidth);
        int heightRatio = Integer.compare(height, this.myHeight);
        int tmpWidth = this.myWidth + widthRatio, tmpHeight = this.myHeight + heightRatio;
        while (tmpHeight != height || tmpWidth != width){
            if (tmpHeight == 0 || tmpWidth == 0){
                break;
            }
            if (map[tmpHeight-1][tmpWidth-1] == color){
                if (print) {
                    System.out.println("The way is blocked by your own piece!!");
                }
                return false;
            }
            tmpHeight += heightRatio;
            tmpWidth += widthRatio;
        }
        return true;
    }

    /**
     * This method move the piece
     *
     * @param width myWidth of piece
     * @param height myHeight of piece
     * @param map map that pieces are there
     * @return a boolean that indicate if the piece moved or not
     */
    public boolean isMoving(int width, int height, char[][] map){
        if (map[height-1][width-1] == '0'){
            return correctMove(width, height, map);
        } else if (map[height-1][width-1] != '0' && map[height-1][width-1] == color){
            System.out.println("This place is filled by your own piece!!");
            return false;
        } else {
            for (Piece piece : pieces) {
                if (piece.getMyHeight() == height && piece.getMyWidth() == width) {
                    piece.setIn(false);
                }
            }
            System.out.println("You hit the enemy's piece!!");
            return correctMove(width, height, map);
        }
    }

    /**
     * this is a abstract method that needs to be defined in subclasses
     *
     * @param width myWidth of piece
     * @param height myHeight of piece
     * @param map map that pieces are there
     * @param move move piece or not
     * @return a boolean that indicate if the piece moved or not
     */
    abstract boolean move(int width, int height, char[][] map, boolean move);

}
