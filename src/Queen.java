public class Queen extends Piece {

    public Queen(int width, int height, boolean isIn, char color) {
        super(width, height, isIn, color);
    }

    @Override
    boolean move(int width, int height, char[][] map) {
        if (Math.abs(this.width-this.height) == Math.abs(width-height) || (this.width == width && height != this.height) || (this.height == height && width != this.width)){
            if (map[height][width] == '0'){
                map[this.height][this.width] = '0';
                map[height][width] = color;
                return true;
            } else if (map[height][width] != '0' && map[height][width] == color){
                System.out.println("This place is filled by your own piece!!");
                return false;
            } else {
                for (Piece piece : pieces) {
                    if (piece.getHeight() == height && piece.getWidth() == width) {
                        piece.setIn(false);
                    }
                }
                System.out.println("You hit the enemy piece!!");
                map[this.height][this.width] = '0';
                map[height][width] = color;
                return true;
            }
        } else {
            System.out.println("This piece can't move to your selected place!!");
            return false;
        }
    }
}
