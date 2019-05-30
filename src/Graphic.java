import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Graphic implements MouseListener, Runnable {
    private boolean clicked;
    private char turn;
    private boolean crisis;
    private Piece clickedPiece;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel whiteOutPanel;
    private JPanel blackOutPanel;
    private JButton[][] map;
    private JOptionPane pane;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean first;


    public Graphic(char color){
        pane = new JOptionPane();
        clickedPiece = null;
        mainFrame = new JFrame("Chess");
        mainPanel = new JPanel();
        whiteOutPanel = new JPanel();
        blackOutPanel = new JPanel();
        map = new JButton[8][8];
        clicked = false;
        turn = color;
        crisis = false;
        Socket socket = null;
        in = null;
        out = null;
        first = true;
    }

    public void game() throws IOException, ClassNotFoundException {
        socket = new Socket("127.0.0.1", 8080);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(500, 700));
        mainFrame.setResizable(false);
        whiteOutPanel.setBackground(new Color(139, 94, 25));
        blackOutPanel.setBackground(new Color(139, 94, 25));
        whiteOutPanel.setPreferredSize(new Dimension(500, 100));
        blackOutPanel.setPreferredSize(new Dimension(500, 100));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        whiteOutPanel.setLayout(new GridLayout(2, 8));
        blackOutPanel.setLayout(new GridLayout(2, 8));
        mainFrame.add(whiteOutPanel, BorderLayout.PAGE_START);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(blackOutPanel, BorderLayout.PAGE_END);
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

    private void checkKing(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (map[i][j] instanceof King){
                    for (Piece piece : Piece.getPieces()){
                        if (piece.getColor() != ((Piece)map[i][j]).getColor()) {
                            if (piece.canWays(map).contains("" + ((Piece) map[i][j]).getMyHeight() + ((Piece) map[i][j]).getMyWidth())) {
                                map[i][j].setBackground(Color.ORANGE);
                            }
                        }
                    }
                }
            }
        }
    }

    private void finish(char winner){
        if (winner == 'W') {
            JOptionPane.showMessageDialog(mainPanel, "White wins!!");
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Black wins!!");
        }
        System.exit(0);
    }

    public void swap(Piece org, JButton target){
        System.out.println("Transferred");
        if (org instanceof Soldier){
            ((Soldier)org).setFirst(false);
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (map[i][j] == org){
                    JButton btt = new JButton();
                    btt.addMouseListener(this);
                    map[i][j] = btt;
                }
            }
        }
        if (target instanceof Piece) {
            System.out.println(target instanceof King);
            if (target instanceof King){
                if (((Piece)target).getColor() == 'W'){
                    finish('B');
                } else {
                    finish('W');
                }
            }
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (map[i][j] == target){
                        JButton btt = map[i][j];
                        btt.setBorder(null);
                        btt.removeMouseListener(this);
                        btt.setBackground(new Color(139, 94, 25));
                        if (org.getColor() == 'W'){
                            blackOutPanel.add(btt);
                        } else {
                            whiteOutPanel.add(btt);
                        }
                        map[i][j] = org;
                        org.setMyHeight(i);
                        org.setMyWidth(j);
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (map[i][j] == target){
                        map[i][j] = org;
                        org.setMyHeight(i);
                        org.setMyWidth(j);
                    }
                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!clicked && !(e.getSource() instanceof Piece)){
            System.out.println("nothing");
            return;
        }
        if (!clicked){
            System.out.println("Now choose where you want to go");
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
            swap(clickedPiece, (JButton) e.getSource());
            try {
                out.writeUTF("" + clickedPiece.getMyHeight() + clickedPiece.getMyWidth() + clickedPiece.getID() + clickedPiece.getColor());
                System.out.println("sent");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            clicked = false;
            clickedPiece = null;
            paint(map, mainPanel);
            mainPanel.repaint();
            checkKing();
        } else {
            System.out.println("Cancelled");
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

    @Override
    public void run() {
        String code = null;
        Piece org = null;
        JButton target = null;
        while (true) {
            try {
                code = in.readUTF();
                System.out.println(code);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (map[i][j] instanceof Piece && ((Piece) map[i][j]).getColor() == code.charAt(3) && ((Piece) map[i][j]).getID() == code.charAt(2)) {
                        org = (Piece) map[i][j];
                    }
                    if (i == code.charAt(0) && j == code.charAt(1)) {
                        target = map[i][j];
                    }
                }
            }
            System.out.println(1);
            swap(org, target);
            paint(map, mainPanel);
            mainPanel.repaint();
            checkKing();
            System.out.println(2);
        }
    }
}
