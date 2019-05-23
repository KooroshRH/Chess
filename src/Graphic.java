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
        turn = 'B';
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
                map[i][j].setFocusable(false);
                if ((i + j)%2 == 0){
                    map[i][j].setBackground(Color.WHITE);
                } else {
                    map[i][j].setBackground(Color.darkGray);
                }
                mainPanel.add(map[i][j]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(((JButton)e.getSource()).getBackground() == Color.RED);
        if (!clicked && !(e.getSource() instanceof Piece)){
            System.out.println("nothing");
            return;
        }
        if (!clicked){
            System.out.println("first");
            if (((Piece)e.getSource()).getColor() == turn){
                ((Piece) e.getSource()).setBackground(Color.GREEN);
                clickedPiece = (Piece)e.getSource();
                for (String place : ((Piece) e.getSource()).canWays(map)){
                    int y = Integer.parseInt("" + place.charAt(0));
                    int x = Integer.parseInt("" + place.charAt(1));
                    map[y][x].setBackground(Color.RED);
                }
                ((Piece)e.getSource()).getPlaces().removeAll(((Piece)e.getSource()).getPlaces());
                clicked = true;
            } else {
                System.out.println("not your turn");
            }
        } else if (((JButton)e.getSource()).getBackground() == Color.RED) {
            System.out.println("correct");
            if (clickedPiece instanceof Soldier){
                System.out.println("seted");
                ((Soldier)clickedPiece).setFirst(false);
            }
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (map[i][j] == clickedPiece){
                        JButton btt = new JButton();
                        btt.addMouseListener(this);
                        map[i][j] = btt;
                    }
                }
            }
            if (e.getSource() instanceof Piece) {
                for (int i = 0; i < 8; i++){
                    for (int j = 0; j < 8; j++){
                        if (map[i][j] == e.getSource()){
                            //TODO exited board
                            map[i][j] = clickedPiece;
                            clickedPiece.setMyHeight(i);
                            clickedPiece.setMyWidth(j);

                        }
                    }
                }
            } else {
                for (int i = 0; i < 8; i++){
                    for (int j = 0; j < 8; j++){
                        if (map[i][j] == e.getSource()){
                            map[i][j] = clickedPiece;
                            clickedPiece.setMyHeight(i);
                            clickedPiece.setMyWidth(j);
                        }
                    }
                }
            }
            if (turn == 'W'){
                turn = 'B';
            } else {
                turn = 'W';
            }
            clicked = false;
            clickedPiece = null;
            paint(map, mainPanel);
            mainPanel.repaint();
        } else {
            clicked = false;
            clickedPiece = null;
            paint(map, mainPanel);
            mainPanel.repaint();
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
