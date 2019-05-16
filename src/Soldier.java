import javax.swing.*;

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

    @Override
    boolean move(int width, int height, JButton[][] map, boolean move) {
        if (color == 'W') {
            if (width == this.myWidth) {
                if (height - this.myHeight == 2 && isFirst) {
                    if (move) {
                        isFirst = false;
                        return isMoving(width, height, map);
                    } else {
                        return true;
                    }
                } else if (height - this.myHeight == 1) {
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
            } else {
                if (map[height][width] instanceof Piece && ((Piece)map[height][width]).getColor() == 'B' && (height - this.myHeight == 1 && (Math.abs(this.myWidth - width) == 1))) {
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
        } else {
            if (width == this.myWidth) {
                if (this.myHeight - height == 2 && isFirst) {
                    if (move) {
                        isFirst = false;
                        return isMoving(width, height, map);
                    } else {
                        return true;
                    }
                } else if (this.myHeight - height == 1) {
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
            } else {
                if (map[height][width] instanceof Piece && ((Piece)map[height][width]).getColor() == 'W' && (this.myHeight - height == 1 && (Math.abs(this.myWidth - width) == 1))) {
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
    }
}
