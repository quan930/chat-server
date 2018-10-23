package app.mrquan.control;

import app.mrquan.factory.Factory;
import app.mrquan.factory.ServerFactory;
import app.mrquan.server.IServer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ServerRoom {
    private Integer loginPort;
    private Integer messagePort;
    private Integer adminPort;
    private String address;
    private IServer loginServer;
    private IServer messageServer;
    private IServer adminServer;

    public ServerRoom(String address,Integer loginPort,Integer messagePort,Integer adminPort){
        this.address = address;
        this.loginPort = loginPort;
        this.messagePort = messagePort;
        this.adminPort = adminPort;
    }

    public void start(){
        loginServer = ServerFactory.getIServerInstance(ServerFactory.SERVER_LOGIN,this);
        new Thread(loginServer).start();
        messageServer = ServerFactory.getIServerInstance(ServerFactory.SERVER_MESSAGE,this);
        new Thread(messageServer).start();
        adminServer = ServerFactory.getIServerInstance(ServerFactory.SERVER_ADMIN,this);
        new Thread(adminServer).start();
    }

    public Integer getLoginPort() {
        return loginPort;
    }

    public Integer getMessagePort() {
        return messagePort;
    }

    public Integer getAdminPort() {
        return adminPort;
    }

    public IServer getLoginServer() {
        return loginServer;
    }

    public IServer getMessageServer() {
        return messageServer;
    }

    public IServer getAdminServer() {
        return adminServer;
    }

    public String getAddress() {
        return address;
    }
}
