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

    @Override
    ArrayList<String> canWays(JButton[][] map) {
        if (this.color == 'W'){
            if (isFirst){
                for (int i = 1; i <= 2; i++){
                    places.add("" + (this.myHeight+i) + (this.myWidth));
                }
            } else {
                if (!(map[this.myHeight+1][this.myWidth] instanceof Piece)){
                    places.add("" + (this.myHeight+1) + (this.myWidth));
                }
                if (map[this.myHeight+1][this.myWidth+1] instanceof  Piece && ((Piece) map[this.myHeight+1][this.myWidth+1]).getColor() != this.getColor()){
                    places.add("" + (this.myHeight+1) + (this.myWidth+1));
                }
                if (map[this.myHeight+1][this.myWidth-1] instanceof  Piece && ((Piece) map[this.myHeight+1][this.myWidth-1]).getColor() != this.getColor()){
                    places.add("" + (this.myHeight+1) + (this.myWidth-1));
                }
            }
        } else {
            if (isFirst){
                for (int i = 1; i <= 2; i++){
                    places.add("" + (this.myHeight-i) + (this.myWidth));
                }
            } else {
                if (!(map[this.myHeight-1][this.myWidth] instanceof Piece)){
                    places.add("" + (this.myHeight-1) + (this.myWidth));
                }
                if (map[this.myHeight-1][this.myWidth+1] instanceof  Piece && ((Piece) map[this.myHeight-1][this.myWidth+1]).getColor() != this.getColor()){
                    places.add("" + (this.myHeight-1) + (this.myWidth+1));
                }
                if (map[this.myHeight-1][this.myWidth-1] instanceof  Piece && ((Piece) map[this.myHeight-1][this.myWidth-1]).getColor() != this.getColor()){
                    places.add("" + (this.myHeight-1) + (this.myWidth-1));
                }
            }
        }
        return places;
    }


}
