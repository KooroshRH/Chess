import javax.swing.*;

public class King extends Piece {

    public King(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map, boolean move) {
        if (Math.abs(this.width - width) <= 1 && Math.abs(this.height - height) <= 1){
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
}
