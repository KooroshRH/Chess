public class Bishop extends Piece {

    public Bishop(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if (Math.abs(this.width-this.height) == Math.abs(width-height)){
            if (!wayKeeper(width, height, map)){
                return false;
            }
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
