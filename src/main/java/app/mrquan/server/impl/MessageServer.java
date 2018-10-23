package app.mrquan.server.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.server.chatter.MessageChatter;
import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;
import app.mrquan.server.chatter.MessageWriterChatter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MessageServer implements IServer {
    /**
     * 消息服务器
     */
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private List<Socket> clients = new ArrayList<>();
    private String path;//文件位置
    private ServerSocket serverSocket;
    private Integer port;
    private Integer loginPort;
    private String address;

    public MessageServer(ServerRoom serverRoom){
        this.port = serverRoom.getMessagePort();
        this.address = serverRoom.getAddress();
        this.loginPort = serverRoom.getLoginPort();
        this.path = serverRoom.getMessagePath();
    }

    public void finish() {
        try {
            serverSocket.close();
            close();
            cachedThreadPool.shutdown();
            DAOFactory.getIMessageDAOInstance().save(path);//处理缓存消息
            System.out.println("消息服务器结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            while (true){
                client = serverSocket.accept();
                add(client);
                cachedThreadPool.execute(new MessageChatter(client,address,loginPort,cachedThreadPool));
            }
        }catch (SocketException ignored){
//            System.out.println("关闭消息服务器");
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