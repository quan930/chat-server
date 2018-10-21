import java.net.ServerSocket;

public class Service implements Runnable {
    private volatile boolean isRunning = true;
    public void run() {
        while(isRunning) {
            System.out.println("hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void finish() {
        isRunning = false;
    }
}
