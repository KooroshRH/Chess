import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A type of piece that extends the Piece class
 *
 * @author Korosh Roohi
 * @version 1.0.0
 * @since 2019.5.13
 */
public class Rook extends Piece {

    public Rook(int myWidth, int myHeight, boolean isIn, char color, int ID) {
        super(myWidth, myHeight, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, JButton[][] map, boolean move) {
        if ((this.myWidth == width && height != this.myHeight) || (this.myHeight == height && width != this.myWidth)){
            if (!wayKeeper(width, height, map, true)){
                return false;
            }
            if (move) {
                return isMoving(width, height, map);
            } else {
                return true;
            }
        } else {
            if (move) {
                System.out.println("This piece can't move to your selected place!!");
            }
            return false;
        }
    }

    @Override
    ArrayList<String> canWays(JButton[][] map) {
        ArrayList<String> places = new ArrayList<>();
        for (int i = 1; this.myHeight + i < 8; i++){
            if (map[this.myHeight+i][this.myWidth] instanceof Piece && ((Piece)map[this.myHeight+i][this.myWidth]).getColor() == this.getColor()){
                break;
            } else {
                places.add( "" + (this.myHeight+i) + this.myWidth);
            }
        }
        for (int i = 1; this.myHeight - i >= 0; i++){
            if (map[this.myHeight-i][this.myWidth] instanceof Piece && ((Piece)map[this.myHeight-i][this.myWidth]).getColor() == this.getColor()){
                break;
            } else {
                places.add( "" + (this.myHeight-i) + this.myWidth);
            }
        }
        for (int i = 1; this.myWidth + i < 8; i++){
            if (map[this.myHeight][this.myWidth+i] instanceof Piece && ((Piece)map[this.myHeight][this.myWidth+i]).getColor() == this.getColor()){
                break;
            } else {
                places.add( "" + this.myHeight + (this.myWidth+i));
            }
        }
        for (int i = 1; this.myWidth - i >= 0; i++){
            if (map[this.myHeight][this.myWidth-i] instanceof Piece && ((Piece)map[this.myHeight][this.myWidth-i]).getColor() == this.getColor()){
                break;
            } else {
                places.add("" +  this.myHeight + (this.myWidth-i) );
            }
        }
        return places;
    }
}
