public class Rook extends Piece {

    public Rook(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if ((this.width == width && height != this.height) || (this.height == height && width != this.width)){
            if (!wayKeeper(width, height, map, true)){
                return false;
            }
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
