package app.mrquan.server.impl;

import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;
import app.mrquan.server.chatter.AdminChatter;
import app.mrquan.server.chatter.MessageChatter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class AdminServer implements IServer {
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    private List<Socket> clients = new ArrayList<>();
    private IServer loginServer;
    private IServer messageServer;
    private ServerSocket serverSocket;
    private Integer port;

    public AdminServer(ServerRoom serverRoom){
        this.port = serverRoom.getAdminPort();
        this.loginServer = serverRoom.getLoginServer();
        this.messageServer = serverRoom.getMessageServer();
    }

    public void finish() {
        try {
            serverSocket.close();
            close();
            fixedThreadPool.shutdown();
            System.out.println("管理员服务器结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getThreadCount() {
        return ((ThreadPoolExecutor)fixedThreadPool).getActiveCount();//线程活跃数
    }

    public void run() {
        try {
            if (port==null){
                return;
            }
            serverSocket = new ServerSocket(port);
            Socket client;
            while (true){
                client = serverSocket.accept();
                add(client);
                fixedThreadPool.execute(new AdminChatter(client,this,loginServer,messageServer));
            }

        } catch (SocketException ignored){

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(Socket client){
        synchronized (this){
            clients.add(client);
        }
    }

    private void close() throws IOException {
        synchronized (this){
            for (Socket client : clients) {
                client.close();
            }
        }
    }
}
