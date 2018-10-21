import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTimer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);
        final Socket socket = serverSocket.accept();
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("!!!!!!!!!!");
                try {
                    writer.write("!!!!!!");
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    timer.cancel();
                    e.printStackTrace();
                }
            }
        },0,1000);
        System.out.println("~~~~~~~~~~~~~~~~~~~");
    }
}
