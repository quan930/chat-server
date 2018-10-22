import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        Main.id = id;
    }

    private static int id = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7005);
//        System.out.println(serverSocket.isClosed());
//        serverSocket.close();
//        System.out.println(serverSocket.isClosed());
        Socket client = serverSocket.accept();
        client.getInputStream().read();
        client.close();
        client.close();
    }
}
