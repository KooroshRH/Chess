public class Bishop extends Piece {

    public Bishop(int width, int height, boolean isIn, char color) {
        super(width, height, isIn, color);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if (Math.abs(this.width-this.height) == Math.abs(width-height)){
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
