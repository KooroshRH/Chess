import javax.swing.*;
import java.util.ArrayList;

abstract class Piece extends JButton {
    protected int ID;
    protected int width;
    protected int height;
    protected boolean isIn;
    protected char color;
    protected static ArrayList<Piece> pieces = new ArrayList<>();

    public Piece(int width, int height, boolean isIn, char color, int ID) {
        this.width = width;
        this.height = height;
        this.isIn = isIn;
        this.color = color;
        this.ID = ID;
        pieces.add(this);
    }

    public char getColor() {
        return color;
    }

    public static ArrayList<Piece> getPieces() {
        return pieces;
    }

    public int getID() {
        return ID;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    private boolean correctMove(int width, int height, char[][] map){
        map[this.height-1][this.width-1] = '0';
        map[height-1][width-1] = color;
        this.height = height;
        this.width = width;
        return true;
    }

    public boolean wayKeeper(int width, int height, char[][] map, boolean print){
        int widthRatio = Integer.compare(width, this.width);
        int heightRatio = Integer.compare(height, this.height);
        int tmpWidth = this.width + widthRatio, tmpHeight = this.height + heightRatio;
        while (tmpHeight != height || tmpWidth != width){
            if (map[tmpHeight-1][tmpWidth-1] == color){
                if (print) {
                    System.out.println("The way is blocked by your own piece!!");
                }
                return false;
            }
            tmpHeight += heightRatio;
            tmpWidth += widthRatio;
        }
        return true;
    }

    public boolean isMoving(int width, int height, char[][] map){
        if (map[height-1][width-1] == '0'){
            return correctMove(width, height, map);
        } else if (map[height-1][width-1] != '0' && map[height-1][width-1] == color){
            System.out.println("This place is filled by your own piece!!");
            return false;
        } else {
            for (Piece piece : pieces) {
                if (piece.getHeight() == height && piece.getWidth() == width) {
                    piece.setIn(false);
                }
            }
            System.out.println("You hit the enemy's piece!!");
            return correctMove(width, height, map);
        }
    }

    abstract boolean move(int width, int height, char[][] map);

}
