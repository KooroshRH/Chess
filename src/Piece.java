import javax.swing.JButton;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

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
    protected ArrayList<String> places;
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
        places = new ArrayList<>();
    }

    public char getColor() {
        return color;
    }

    public ArrayList<String> getPlaces() {
        return places;
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

    public void setMyWidth(int myWidth) {
        this.myWidth = myWidth;
    }

    public void setMyHeight(int myHeight) {
        this.myHeight = myHeight;
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
    private boolean correctMove(int width, int height, JButton[][] map){
        JButton tmpButton = map[this.myHeight][this.myWidth];
        map[this.myHeight][this.myWidth] = map[height][width];
        map[height][width] = tmpButton;
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
    public boolean wayKeeper(int width, int height, JButton[][] map, boolean print){
        int widthRatio = Integer.compare(width, this.myWidth);
        int heightRatio = Integer.compare(height, this.myHeight);
        int tmpWidth = this.myWidth + widthRatio, tmpHeight = this.myHeight + heightRatio;
        while (tmpHeight != height-1 || tmpWidth != width-1){
            if (tmpHeight == 0 || tmpWidth == 0){
                break;
            }
            if (map[tmpHeight][tmpWidth] instanceof Piece){
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
    public boolean isMoving(int width, int height, JButton[][] map){
        if (!(map[height][width] instanceof Piece)){
            return correctMove(width, height, map);
        } else if (map[height][width] instanceof Piece && ((Piece)map[height][width]).getColor() == color){
            System.out.println("This place is filled by your own piece!!");
            return false;
        } else {
            for (Piece piece : pieces) {
                if (piece.getMyHeight() == height && piece.getMyWidth() == width) {
                    map[height][width].setIcon(null);
                    piece.setIn(false);
                }
            }
            System.out.println("You hit the enemy's piece!!");
            return correctMove(width, height, map);
        }
    }

    protected ArrayList<String> tiltCheck(ArrayList<String> places, JButton[][] map){
        for (int i = 1; this.myWidth+i < 8 && this.myHeight+i < 8; i++){
            if (!(map[this.myHeight+i][this.myWidth+i] instanceof Piece) || ((Piece) map[this.myHeight+i][this.myWidth+i]).getColor() != this.getColor()){
                places.add("" + (this.myHeight+i) + (this.myWidth+i));
                if (map[this.myHeight+i][this.myWidth+i] instanceof Piece){
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; this.myWidth-i >= 0 && this.myHeight-i >= 0; i++){
            if (!(map[this.myHeight-i][this.myWidth-i] instanceof Piece) || ((Piece) map[this.myHeight-i][this.myWidth-i]).getColor() != this.getColor()){
                places.add("" + (this.myHeight-i) + (this.myWidth-i));
                if (map[this.myHeight-i][this.myWidth-i] instanceof Piece){
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; this.myWidth+i < 8 && this.myHeight-i >= 0; i++){
            if (!(map[this.myHeight-i][this.myWidth+i] instanceof Piece) || ((Piece) map[this.myHeight-i][this.myWidth+i]).getColor() != this.getColor()){
                places.add("" + (this.myHeight-i) + (this.myWidth+i));
                if (map[this.myHeight-i][this.myWidth+i] instanceof Piece){
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = 1; this.myWidth-i >= 0 && this.myHeight+i < 8; i++){
            if (!(map[this.myHeight+i][this.myWidth-i] instanceof Piece) || ((Piece) map[this.myHeight+i][this.myWidth-i]).getColor() != this.getColor()){
                places.add("" + (this.myHeight+i) + (this.myWidth-i));
                if (map[this.myHeight+i][this.myWidth-i] instanceof Piece){
                    break;
                }
            } else {
                break;
            }
        }
        return places;
    }

    protected ArrayList<String> linearCheck(ArrayList<String> places, JButton[][] map){
        for (int i = 1; this.myHeight + i < 8; i++){
            if (map[this.myHeight+i][this.myWidth] instanceof Piece && ((Piece)map[this.myHeight+i][this.myWidth]).getColor() == this.getColor()){
                break;
            } else {
                places.add( "" + (this.myHeight+i) + this.myWidth);
                if (map[this.myHeight+i][this.myWidth] instanceof Piece){
                    break;
                }
            }
        }
        for (int i = 1; this.myHeight - i >= 0; i++){
            if (map[this.myHeight-i][this.myWidth] instanceof Piece && ((Piece)map[this.myHeight-i][this.myWidth]).getColor() == this.getColor()){
                break;
            } else {
                places.add( "" + (this.myHeight-i) + this.myWidth);
                if (map[this.myHeight-i][this.myWidth] instanceof Piece){
                    break;
                }
            }
        }
        for (int i = 1; this.myWidth + i < 8; i++){
            if (map[this.myHeight][this.myWidth+i] instanceof Piece && ((Piece)map[this.myHeight][this.myWidth+i]).getColor() == this.getColor()){
                break;
            } else {
                places.add( "" + this.myHeight + (this.myWidth+i));
                if (map[this.myHeight][this.myWidth+i] instanceof Piece){
                    break;
                }
            }
        }
        for (int i = 1; this.myWidth - i >= 0; i++){
            if (map[this.myHeight][this.myWidth-i] instanceof Piece && ((Piece)map[this.myHeight][this.myWidth-i]).getColor() == this.getColor()){
                break;
            } else {
                places.add("" +  this.myHeight + (this.myWidth-i) );
                if (map[this.myHeight][this.myWidth-i] instanceof Piece){
                    break;
                }
            }
        }
        return places;
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
    abstract boolean move(int width, int height, JButton[][] map, boolean move);
    abstract ArrayList<String> canWays(JButton[][] map);
}
