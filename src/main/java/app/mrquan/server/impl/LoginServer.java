package app.mrquan.server.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.server.chatter.LoginChatter;
import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class LoginServer implements IServer {
    /**
     * 登录服务器
     */
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    private List<Socket> clients = new ArrayList<>();
    private String path;//文件位置
    private Integer port;//登录服务端口号
    private ServerSocket serverSocket;
    private Integer messagePort;
    private String address;

    public LoginServer(ServerRoom serverRoom){
        this.port = serverRoom.getLoginPort();
        this.address = serverRoom.getAddress();
        this.messagePort = serverRoom.getMessagePort();
        this.path = serverRoom.getTokenPath();
    }

    public void finish() {
        try {
            serverSocket.close();//服务器关闭
            close();//客户段套接字关闭
            fixedThreadPool.shutdown();//线程池关闭
            if (DAOFactory.getITokenDAOInstance().save(path)==1){//处理缓存消息
                System.out.println("登陆服务器:保存数据成功");
            }else {
                System.out.println("登陆服务器:保存数据失败");
            }
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
                fixedThreadPool.execute(new LoginChatter(client,address,messagePort));
            }
        }catch (SocketException ignored){
//            System.out.println("关闭登陆服务器");
        }catch (IOException e) {
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
