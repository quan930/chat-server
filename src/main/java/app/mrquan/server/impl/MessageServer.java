package app.mrquan.server.impl;

import app.mrquan.chatter.LoginChatter;
import app.mrquan.chatter.MessageChatter;
import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MessageServer implements IServer {
    /**
     * 线程服务器
     */
    private volatile boolean isRunning = true;
    private Integer port;
    private Integer loginPort;
    private String loginAddress;
    private ServerSocket serverSocket;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();



    public MessageServer(ServerRoom serverRoom){
        this.port = serverRoom.getMessagePort();
        this.loginAddress = serverRoom.getLoginAddress();
        this.loginPort = serverRoom.getLoginPort();
    }

    public void finish() {
        isRunning = false;
    }

    public int getThreadCount() {
        return ((ThreadPoolExecutor)cachedThreadPool).getActiveCount();//线程活跃数
    }

    public void run() {
        try {
            if (port==null){
                return;
            }
            serverSocket = new ServerSocket(port);
            Socket client;
            while (!serverSocket.isClosed()){
                client = serverSocket.accept();
                cachedThreadPool.execute(new MessageChatter(client,loginAddress,loginPort));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
