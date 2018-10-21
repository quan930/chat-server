import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws IOException {
//        Service service = new Service();
//        Thread thread = new Thread(service);
//        thread.start();
//        Scanner in = new Scanner(System.in);
//        in.nextLine();
//        service.finish();
//        ServerSocket serverSocket = new ServerSocket(8888);
//        serverSocket.accept();
//        serverSocket.accept();
//        serverSocket.close();
//        while (true){
//            serverSocket.accept();
//        }

        Main main = new Main();
        System.out.println(main.getId());
        main.setId(3);
        Main main1 = new Main();
        System.out.println(main1.getId());

    }
}
