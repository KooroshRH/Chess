public class Rook extends Piece {

    public Rook(int width, int height, boolean isIn, char color) {
        super(width, height, isIn, color);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if ((this.width == width && height != this.height) || (this.height == height && width != this.width)){
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
