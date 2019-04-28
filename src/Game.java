import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private char[][] map = new char[8][8];
    public void play(){
        Scanner input = new Scanner(System.in);
        printInstruction();
        makePieces('W');
        makePieces('B');
        mapMaker(map, Piece.getPieces());
        int paces = 0;
        while (true){
            char color;
            if (paces%2 == 0){
                System.out.println("It's white turn");
                color = 'W';
            } else {
                System.out.println("It's black turn");
                color = 'B';
            }
            printMap();
            System.out.print("Which piece do you want to move?(Enter ID): ");
            int ID = input.nextInt();
            Piece piece = null;
            for (Piece piece1 : Piece.getPieces()){
                if (color == piece1.getColor() && ID == piece1.getID()){
                    piece = piece1;
                    break;
                }
            }
            System.out.println("Now enter the place that you want to go to(for example 'a3'): ");
            input.nextLine();
            String str = input.nextLine();
            int width = str.charAt(0) - 96;
            int height = Integer.parseInt("" + str.charAt(1));
            if (!piece.move(width, height, map)){
                System.out.println("You must choose another time");
                continue;
            }
            paces++;
        }
    }

    private void printInstruction(){
        System.out.println("Welcome to chess game");
        System.out.println("For each color 1-8 id's are for soldiers");
        System.out.println("9 & 16 are Rooks");
        System.out.println("10 & 15 are Horses");
        System.out.println("11 & 14 are Bishops");
        System.out.println("12 is Queen and 13 is King");
        System.out.println("-----------------------------------------------");
        System.out.println("How to play?");
        System.out.println("First you will see a map without pieces");
        System.out.println("Then first white side will choose a piece\nThen choose a place for that piece to go");
        System.out.println("The game will continue until one side's king matted!!!");
        System.out.println("Press any key to continue...");
        Scanner input = new Scanner(System.in);
        input.next();
    }

    private void printMap(){
        System.out.println(" a b c d e f g h ");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                System.out.print("|" + map[i][j]);
            }
            System.out.println("|" + (i+1));
        }
    }

    private void makePieces(char color){
        if (color == 'B') {
            for (int i = 1; i <= 8; i++) {
                new Soldier(i, 7, true, color, i);
            }
            new Rook(1, 8, true, color, 9);
            new Rook(8, 8, true, color, 16);
            new Horse(2, 8, true, color, 10);
            new Horse(7, 8, true, color, 15);
            new Bishop(3, 8, true, color, 11);
            new Bishop(6, 8, true, color, 14);
            new Queen(4, 8, true, color, 12);
            new King(5, 8, true, color, 13);
        } else {
            for (int i = 1; i <= 8; i++) {
                new Soldier(i, 2, true, color, i);
            }
            new Rook(1, 1, true, color, 9);
            new Rook(8, 1, true, color, 16);
            new Horse(2, 1, true, color, 10);
            new Horse(7, 1, true, color, 15);
            new Bishop(3, 1, true, color, 11);
            new Bishop(6, 1, true, color, 14);
            new Queen(4, 1, true, color, 12);
            new King(5, 1, true, color, 13);
        }
    }

    public void mapMaker(char[][] map, ArrayList<Piece> pieces){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                map[j][i] = '0';
            }
        }
        for (Piece piece : pieces){
            map[piece.getHeight()-1][piece.getWidth()-1] = piece.getColor();
        }
    }
}
