import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Thread server = new GameServer();
        server.start();
        Graphic white = new Graphic('W');
        Graphic black = new Graphic('B');
        white.game();
        black.game();
        Thread whiteThr = new Thread(white);
        Thread blackThr = new Thread(black);
        whiteThr.start();
        blackThr.start();
    }
}
