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
    boolean move(int width, int height, JButton[][] map, boolean move) {
        if (Math.abs(this.myWidth - width) <= 1 && Math.abs(this.myHeight - height) <= 1){
            for (Piece piece : pieces){
                System.out.println(piece.getID());
                System.out.println(piece.color);
                if (piece.move(width, height, map, false) && piece.color != this.color){
                    System.out.println("The king can't go there!!!");
                    return false;
                }
            }
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }

    @Override
    ArrayList<String> canWays(JButton[][] map) {
        return null;
    }


}
