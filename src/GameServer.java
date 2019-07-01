import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {
    private ServerSocket server;
    private Socket white;
    private Socket black;
    private DataOutputStream outBlack;
    private DataOutputStream outWhite;
    private DataInputStream inBlack;
    private DataInputStream inWhite;

    public GameServer() throws IOException {
        server = new ServerSocket(8080);
        outBlack = null;
        outWhite = null;
        inBlack = null;
        inWhite = null;
        white = null;
        black = null;
    }

    @Override
    public void run() {
        try {
            white = server.accept();
            black = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Game Server is Activated");
        try {
            outWhite = new DataOutputStream(white.getOutputStream());
            outBlack = new DataOutputStream(black.getOutputStream());
            inWhite = new DataInputStream(white.getInputStream());
            inBlack = new DataInputStream(black.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int move = 0;
        try {
            while (true) {
                if (move % 2 == 0) {
                    String code = inWhite.readUTF();
                    outBlack.writeUTF(code);
                    move++;
                } else {
                    String code = inBlack.readUTF();
                    outWhite.writeUTF(code);
                    move++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
