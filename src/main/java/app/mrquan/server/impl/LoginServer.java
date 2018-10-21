package app.mrquan.server.impl;

import app.mrquan.chatter.LoginChatter;
import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class LoginServer implements IServer {
    /**
     * 登录服务器
     */
    private Integer port;//登录服务端口号
    private ServerSocket serverSocket;
    private Integer messagePort;
    private String messageAddress;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    public LoginServer(ServerRoom serverRoom){
        this.port = serverRoom.getLoginPort();
        this.messageAddress = serverRoom.getMessageAddress();
        this.messagePort = serverRoom.getMessagePort();
    }

    public void finish() {
        /**
         * 结束方法有问题
         */
        try {
//            fixedThreadPool.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                fixedThreadPool.execute(new LoginChatter(client,messageAddress,messagePort));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getThreadCount() {
        return ((ThreadPoolExecutor)fixedThreadPool).getActiveCount();//线程活跃数
    }
}
