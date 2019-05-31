import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A type of piece that extends Piece class
 *
 * @author Korosh Roohi
 * @version 1.0.0
 * @since 2019.5.13
 */
public class Soldier extends Piece {
    private boolean isFirst;

    public Soldier(int myWidth, int myHeight, boolean isIn, char color, int ID) {
        super(myWidth, myHeight, isIn, color, ID);
        isFirst = true;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @Override
    ArrayList<String> canWays(JButton[][] map) {
        if (this.color == 'W'){
            if (isFirst){
                for (int i = 1; i <= 2; i++){
                    places.add("" + (this.myHeight+i) + (this.myWidth));
                }
            } else {
                if (this.myHeight+1 < 8) {
                    if (!(map[this.myHeight + 1][this.myWidth] instanceof Piece)) {
                        places.add("" + (this.myHeight + 1) + (this.myWidth));
                    }
                    if (this.myWidth + 1 < 8) {
                        if (map[this.myHeight + 1][this.myWidth + 1] instanceof Piece && ((Piece) map[this.myHeight + 1][this.myWidth + 1]).getColor() != this.getColor()) {
                            places.add("" + (this.myHeight + 1) + (this.myWidth + 1));
                        }
                    }
                    if (this.myWidth - 1 >= 0) {
                        if (map[this.myHeight + 1][this.myWidth - 1] instanceof Piece && ((Piece) map[this.myHeight + 1][this.myWidth - 1]).getColor() != this.getColor()) {
                            places.add("" + (this.myHeight + 1) + (this.myWidth - 1));
                        }
                    }
                }
            }
        } else {
            if (isFirst){
                for (int i = 1; i <= 2; i++){
                    places.add("" + (this.myHeight-i) + (this.myWidth));
                }
            } else {
                if (this.myHeight-1 >= 0) {
                    if (!(map[this.myHeight - 1][this.myWidth] instanceof Piece)) {
                        places.add("" + (this.myHeight - 1) + (this.myWidth));
                    }
                    if (this.myWidth + 1 < 8) {
                        if (map[this.myHeight - 1][this.myWidth + 1] instanceof Piece && ((Piece) map[this.myHeight - 1][this.myWidth + 1]).getColor() != this.getColor()) {
                            places.add("" + (this.myHeight - 1) + (this.myWidth + 1));
                        }
                    }
                    if (this.myWidth - 1 >= 0) {
                        if (map[this.myHeight - 1][this.myWidth - 1] instanceof Piece && ((Piece) map[this.myHeight - 1][this.myWidth - 1]).getColor() != this.getColor()) {
                            places.add("" + (this.myHeight - 1) + (this.myWidth - 1));
                        }
                    }
                }
            }
        }
        return places;
    }


}
