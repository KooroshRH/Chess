import java.util.Scanner;

public class Game {
    public void play(){
        printInstruction();
        printMap();
        makePieces('W');
        makePieces('B');
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
        System.out.println(" A B C D E F G H ");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                System.out.print("|_");
            }
            System.out.println("|" + (8-i));
        }
    }

    private void makePieces(char color){
        if (color == 'B') {
            for (int i = 1; i <= 8; i++) {
                Piece piece = new Soldier(i, 7, true, color, i);
            }
            {
                Piece piece1 = new Rook(1, 8, true, color, 9);
                Piece piece2 = new Rook(8, 8, true, color, 16);
            }
            {
                Piece piece1 = new Horse(2, 8, true, color, 10);
                Piece piece2 = new Horse(7, 8, true, color, 15);
            }
            {
                Piece piece1 = new Bishop(3, 8, true, color, 11);
                Piece piece2 = new Bishop(6, 8, true, color, 14);
            }
            Piece piece1 = new Queen(4, 8, true, color, 12);
            Piece piece2 = new King(5, 8, true, color, 13);
        } else {
            for (int i = 1; i <= 8; i++) {
                Piece piece = new Soldier(i, 2, true, color, i);
            }
            {
                Piece piece1 = new Rook(1, 2, true, color, 9);
                Piece piece2 = new Rook(8, 2, true, color, 16);
            }
            {
                Piece piece1 = new Horse(2, 2, true, color, 10);
                Piece piece2 = new Horse(7, 2, true, color, 15);
            }
            {
                Piece piece1 = new Bishop(3, 2, true, color, 11);
                Piece piece2 = new Bishop(6, 2, true, color, 14);
            }
            Piece piece1 = new Queen(4, 2, true, color, 12);
            Piece piece2 = new King(5, 2, true, color, 13);
        }
    }
}
