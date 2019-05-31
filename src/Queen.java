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
    ArrayList<String> canWays(JButton[][] map) {
        return tiltCheck(linearCheck(places, map), map);
    }
}
