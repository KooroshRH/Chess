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

    abstract ArrayList<String> canWays(JButton[][] map);
}
