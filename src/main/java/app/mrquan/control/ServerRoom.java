package app.mrquan.control;

import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.ServerFactory;
import app.mrquan.server.IServer;

public class ServerRoom {
    private String address;
    private IServer loginServer;
    private IServer messageServer;
    private IServer adminServer;
    private Integer loginPort;
    private Integer messagePort;
    private Integer adminPort;
    private String tokenPath;
    private String messagePath;

    public ServerRoom(String address,Integer loginPort,Integer messagePort,Integer adminPort,String tokenPath,String messagePath){
        this.address = address;
        this.loginPort = loginPort;
        this.messagePort = messagePort;
        this.adminPort = adminPort;
        this.tokenPath = tokenPath;
        this.messagePath = messagePath;
    }

    public void start(){
        init();
        System.out.println("数据初始化");
        loginServer = ServerFactory.getIServerInstance(ServerFactory.SERVER_LOGIN,this);
        new Thread(loginServer).start();
        messageServer = ServerFactory.getIServerInstance(ServerFactory.SERVER_MESSAGE,this);
        new Thread(messageServer).start();
        adminServer = ServerFactory.getIServerInstance(ServerFactory.SERVER_ADMIN,this);
        new Thread(adminServer).start();
        System.out.println("启动");
    }

    private void init(){
        DAOFactory.getITokenDAOInstance().init(tokenPath);
        DAOFactory.getIMessageDAOInstance().init(messagePath);
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

    public String getTokenPath() {
        return tokenPath;
    }

    public String getMessagePath() {
        return messagePath;
    }
}