public class Queen extends Piece {

    public Queen(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if (Math.abs(this.width-this.height) == Math.abs(width-height) || (this.width == width && height != this.height) || (this.height == height && width != this.width)){
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
