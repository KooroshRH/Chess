public class Soldier extends Piece {
    private boolean isFirst;
    public Soldier(int width, int height, boolean isIn, char color, int ID) {
        super(width, height, isIn, color, ID);
        isFirst = true;
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if (width == this.width){
            if (height - this.height == 2 && isFirst){
                isFirst = false;
                return isMoving(width, height, map);
            } else if (height - this.height == 1){
                return isMoving(width, height, map);
            } else {
                System.out.println("This piece can't move to your selected place!!");
                return false;
            }
        } else {
            if (map[height-1][width-1] == color){
                System.out.println("This place is filled by your own piece!!");
                return false;
            } else {
                return isMoving(width, height, map);
            }
        }
    }
}
