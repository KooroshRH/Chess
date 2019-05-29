import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(8080);
        Socket white = server.accept();
        System.out.println("White is in");
        Socket black = server.accept();
        System.out.println("Black is in");
        System.out.println("Game Server is Activated");
        ObjectOutputStream whiteOut = new ObjectOutputStream(new DataOutputStream(white.getOutputStream()));
        ObjectOutputStream blackOut = new ObjectOutputStream(new DataOutputStream(black.getOutputStream()));
        ObjectInputStream whiteIn = new ObjectInputStream(new DataInputStream(white.getInputStream()));
        ObjectInputStream blackIn = new ObjectInputStream(new DataInputStream(black.getInputStream()));
        int move = 0;
        while(white.isClosed() || black.isClosed()){
            if (move % 2 == 0){
                JButton[][] map = (JButton[][]) whiteIn.readObject();
                blackOut.writeObject(map);
                move++;
            } else {
                JButton[][] map = (JButton[][]) blackIn.readObject();
                whiteOut.writeObject(map);
                move++;
            }
        }
    }
}
