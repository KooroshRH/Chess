import javax.swing.*;
import java.util.ArrayList;

/**
 * A type of piece that extends the Piece class
 *
 * @author Korosh Roohi
 * @version 1.0.0
 * @since 2019.5.13
 */
public class Bishop extends Piece {

    public Bishop(int myWidth, int myHeight, boolean isIn, char color, int ID) {
        super(myWidth, myHeight, isIn, color, ID);
    }

    @Override
    ArrayList<String> canWays(JButton[][] map) {
        return tiltCheck(places, map);
    }


}
