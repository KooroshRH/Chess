import java.util.Scanner;

public class Game {
    public void play(){
        printInstruction();
        printMap();
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
}
