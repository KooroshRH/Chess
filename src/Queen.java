public class Queen extends Piece {

    public Queen(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map, boolean move) {
        if (Math.abs(this.width-this.height) == Math.abs(width-height) || (this.width == width && height != this.height) || (this.height == height && width != this.width)){
            if (!wayKeeper(width, height, map, true)){
                return false;
            }
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
