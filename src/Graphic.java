import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Graphic {
    private JFrame mainFrame;
    private JButton[][] map;

    public Graphic(){
        mainFrame = new JFrame();
        map = new JButton[8][8];
    }

    public void game(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(700, 700));
        mainFrame.setResizable(false);
        JPanel mainPanel = new JPanel();
        JPanel whiteOut = new JPanel();
        JPanel blackOut = new JPanel();
        whiteOut.setSize(new Dimension(500, 900));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(8, 8));
        makePieces('W');
        makePieces('B');
        for (Piece piece : Piece.getPieces()){
            try {
                Image picture = ImageIO.read(new File("C:\\Users\\Korosh\\IdeaProjects\\Chess\\src\\Icons\\" + piece.color + piece.getClass().getName() + ".png"));
                piece.setIcon(new ImageIcon(picture));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            piece.setFocusable(false);
            map[piece.getMyHeight()][piece.getMyWidth()] = piece;
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (!(map[i][j] instanceof Piece)){
                    map[i][j] = new JButton();
                }
            }
        }
        paint(map, mainPanel);
        mainFrame.setVisible(true);
    }

    private void makePieces(char color){
        if (color == 'B') {
            for (int i = 1; i <= 8; i++) {
                new Soldier(i, 6, true, color, i);
            }
            new Rook(0, 7, true, color, 9);
            new Rook(7, 7, true, color, 16);
            new Horse(1, 7, true, color, 10);
            new Horse(6, 7, true, color, 15);
            new Bishop(2, 7, true, color, 11);
            new Bishop(5, 7, true, color, 14);
            new Queen(3, 7, true, color, 12);
            new King(4, 7, true, color, 13);
        } else {
            for (int i = 1; i <= 8; i++) {
                new Soldier(i, 1, true, color, i);
            }
            new Rook(0, 0, true, color, 9);
            new Rook(7, 0, true, color, 16);
            new Horse(1, 0, true, color, 10);
            new Horse(6, 0, true, color, 15);
            new Bishop(2, 0, true, color, 11);
            new Bishop(5, 0, true, color, 14);
            new Queen(3, 0, true, color, 12);
            new King(4, 0, true, color, 13);
        }
    }

    private void paint(JButton[][] map, JPanel mainPanel){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                JButton btt = map[i][j];
                btt.setFocusable(false);
                if ((i + j)%2 == 0){
                    btt.setBackground(Color.WHITE);
                } else {
                    btt.setBackground(Color.darkGray);
                }
                mainPanel.add(btt);
            }
        }
    }
}
