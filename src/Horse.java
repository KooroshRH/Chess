import javax.swing.*;
import java.util.ArrayList;

/**
 * A type of piece that extends the Piece class
 *
 * @author Korosh Roohi
 * @version 1.0.0
 * @since 2019.5.13
 */
public class Horse extends Piece {

    public Horse(int myWidth, int myHeight, boolean isIn, char color, int ID) {
        super(myWidth, myHeight, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, JButton[][] map, boolean move) {
        if ((Math.abs(width - this.myWidth) == 2 && Math.abs(height - this.myHeight) == 1) || (Math.abs(width - this.myWidth) == 1 && Math.abs(height - this.myHeight) == 2)){
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
        if (this.myHeight+1 < 8 && this.myWidth+2 < 8) {
            if (!(map[this.myHeight + 1][this.myWidth + 2] instanceof Piece) || ((Piece) map[this.myHeight + 1][this.myWidth + 2]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight + 1) + (this.myWidth + 2));
            }
        }
        if (this.myHeight-1 >= 0 && this.myWidth+2 < 8) {
            if (!(map[this.myHeight - 1][this.myWidth + 2] instanceof Piece) || ((Piece) map[this.myHeight - 1][this.myWidth + 2]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight - 1) + (this.myWidth + 2));
            }
        }
        if (this.myHeight+1 < 8 && this.myWidth-2 >= 0) {
            if (!(map[this.myHeight + 1][this.myWidth - 2] instanceof Piece) || ((Piece) map[this.myHeight + 1][this.myWidth - 2]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight + 1) + (this.myWidth - 2));
            }
        }
        if (this.myHeight-1 >= 0 && this.myWidth-2 >= 0) {
            if (!(map[this.myHeight - 1][this.myWidth - 2] instanceof Piece) || ((Piece) map[this.myHeight - 1][this.myWidth - 2]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight - 1) + (this.myWidth - 2));
            }
        }
        if (this.myHeight+2 < 8 && this.myWidth+1 < 8) {
            if (!(map[this.myHeight + 2][this.myWidth + 1] instanceof Piece) || ((Piece) map[this.myHeight + 2][this.myWidth + 1]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight + 2) + (this.myWidth + 1));
            }
        }
        if (this.myHeight-2 >= 0 && this.myWidth+1 < 8) {
            if (!(map[this.myHeight - 2][this.myWidth + 1] instanceof Piece) || ((Piece) map[this.myHeight - 2][this.myWidth + 1]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight - 2) + (this.myWidth + 1));
            }
        }
        if (this.myHeight+2 < 8 && this.myWidth-1 >= 0) {
            if (!(map[this.myHeight + 2][this.myWidth - 1] instanceof Piece) || ((Piece) map[this.myHeight + 2][this.myWidth - 1]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight + 2) + (this.myWidth - 1));
            }
        }
        if (this.myHeight-2 >= 0 && this.myWidth-1 >= 0) {
            if (!(map[this.myHeight - 2][this.myWidth - 1] instanceof Piece) || ((Piece) map[this.myHeight - 2][this.myWidth - 1]).getColor() != this.getColor()) {
                places.add("" + (this.myHeight - 2) + (this.myWidth - 1));
            }
        }
        return places;
    }
}
