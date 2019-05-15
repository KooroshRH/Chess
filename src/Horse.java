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
    boolean move(int width, int height, char[][] map, boolean move) {
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
}
