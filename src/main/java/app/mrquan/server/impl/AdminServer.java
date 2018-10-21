package app.mrquan.server.impl;

import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;

import java.util.concurrent.ExecutorService;

public class AdminServer implements IServer {
    private volatile boolean isRunning = true;
    private Integer port;
    private String address;

    public AdminServer(ServerRoom serverRoom){
        port = serverRoom.getAdminPort();
    }

    public void finish() {
        isRunning = false;
    }

    public int getThreadCount() {
        return 0;
    }

    public void run() {
        while(isRunning) {
//            System.out.println("\t\t\t\t\t\t\t\t管理员hello"+port);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
