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
public class Queen extends Piece {

    public Queen(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, JButton[][] map, boolean move) {
        if (Math.abs(this.myWidth -this.myHeight) == Math.abs(width-height) || (this.myWidth == width && height != this.myHeight) || (this.myHeight == height && width != this.myWidth)){
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
        return null;
    }


}
