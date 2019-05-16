import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Graphic implements MouseListener {
    private boolean clicked;
    private char turn;
    private Piece clickedPiece;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton[][] map;

    public Graphic(){
        clickedPiece = null;
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        map = new JButton[8][8];
        clicked = false;
        turn = 'W';
    }

    public void game(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(700, 700));
        mainFrame.setResizable(false);
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
            piece.addMouseListener(this);
            map[piece.getMyHeight()][piece.getMyWidth()] = piece;
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (!(map[i][j] instanceof Piece)){
                    JButton btt = new JButton();
                    btt.addMouseListener(this);
                    map[i][j] = btt;
                }
            }
        }
        paint(map, mainPanel);
        mainFrame.setVisible(true);
    }

    private void makePieces(char color){
        if (color == 'B') {
            for (int i = 0; i < 8; i++) {
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
            for (int i = 0; i < 8; i++) {
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
        mainPanel.removeAll();
        mainPanel.revalidate();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!clicked){
            System.out.println("first");
            if (((Piece)e.getSource()).getColor() == turn){
                ((Piece) e.getSource()).setBackground(Color.GREEN);
                clickedPiece = (Piece)e.getSource();
                clicked = true;
            }
        } else {
            System.out.println("sec");
            JButton target = (JButton) e.getSource();
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (map[i][j] == target){
                        System.out.println("secsec");
                        clickedPiece.move(j, i, map, true);
                        paint(map, mainPanel);
                    }
                }
            }
            if (turn == 'W'){
                turn = 'B';
            } else {
                turn = 'W';
            }
            clickedPiece = null;
            clicked = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
