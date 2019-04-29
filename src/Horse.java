public class Horse extends Piece {

    public Horse(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if ((Math.abs(width - this.width) == 2 && Math.abs(height - this.height) == 1) || (Math.abs(width - this.width) == 1 && Math.abs(height - this.height) == 2)){
            return isMoving(width, height, map);
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
