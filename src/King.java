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
public class King extends Piece {

    public King(int myWidth, int myHeight, boolean isIn, char color, int ID) {
        super(myWidth, myHeight, isIn, color, ID);
    }

    @Override
    ArrayList<String> canWays(JButton[][] map) {
        if (this.myHeight+1 < 8 && this.myWidth+1 < 8){
            if (!(map[this.myHeight+1][this.myWidth+1] instanceof Piece)){
                places.add("" + (this.myHeight+1) + (this.myWidth+1));
            }
        }
        if (this.myHeight-1 >= 0 && this.myWidth+1 < 8){
            if (!(map[this.myHeight-1][this.myWidth+1] instanceof Piece)){
                places.add("" + (this.myHeight-1) + (this.myWidth+1));
            }
        }
        if (this.myHeight+1 < 8 && this.myWidth-1 >= 0){
            if (!(map[this.myHeight+1][this.myWidth-1] instanceof Piece)){
                places.add("" + (this.myHeight+1) + (this.myWidth-1));
            }
        }
        if (this.myHeight-1 >= 0 && this.myWidth-1 >= 0){
            if (!(map[this.myHeight-1][this.myWidth-1] instanceof Piece)){
                places.add("" + (this.myHeight-1) + (this.myWidth-1));
            }
        }
        if (this.myWidth+1 < 8){
            if (!(map[this.myHeight][this.myWidth+1] instanceof Piece)){
                places.add("" + (this.myHeight) + (this.myWidth+1));
            }
        }
        if (this.myWidth-1 >= 0){
            if (!(map[this.myHeight][this.myWidth-1] instanceof Piece)){
                places.add("" + (this.myHeight) + (this.myWidth-1));
            }
        }
        if (this.myHeight+1 < 8){
            if (!(map[this.myHeight+1][this.myWidth] instanceof Piece)){
                places.add("" + (this.myHeight+1) + (this.myWidth));
            }
        }
        if (this.myHeight-1 >= 0){
            if (!(map[this.myHeight-1][this.myWidth] instanceof Piece)){
                places.add("" + (this.myHeight-1) + (this.myWidth));
            }
        }
        return places;
    }
}
