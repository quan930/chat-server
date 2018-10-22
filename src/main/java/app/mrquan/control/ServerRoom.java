package app.mrquan.control;

import app.mrquan.factory.Factory;
import app.mrquan.server.IServer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ServerRoom {
    private Integer loginPort;
    private Integer messagePort;
    private Integer adminPort;
    private String loginAddress;
    private String messageAddress;
    private String adminAddress;
    private IServer loginServer;
    private IServer messageServer;
    private IServer adminServer;

    public ServerRoom(String loginAddress,Integer loginPort,String messageAddress,Integer messagePort,String adminAddress,Integer adminPort){
        this.loginAddress = loginAddress;
        this.loginPort = loginPort;
        this.messageAddress = messageAddress;
        this.messagePort = messagePort;
        this.adminAddress = adminAddress;
        this.adminPort = adminPort;
    }

    public void start(){
        loginServer = Factory.getIServerInstance(Factory.SERVER_LOGIN,this);
        new Thread(loginServer).start();
        messageServer = Factory.getIServerInstance(Factory.SERVER_MESSAGE,this);
        new Thread(messageServer).start();
//        adminServer = Factory.getIServerInstance(Factory.SERVER_ADMIN,this);
//        new Thread(adminServer).start();
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

    public String getLoginAddress() {
        return loginAddress;
    }

    public String getMessageAddress() {
        return messageAddress;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

}
