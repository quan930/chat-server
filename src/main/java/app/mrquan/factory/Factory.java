package app.mrquan.factory;

import app.mrquan.DAO.ITokenDAO;
import app.mrquan.DAO.impl.TokenDAOImpl;
import app.mrquan.control.ServerRoom;
import app.mrquan.server.IServer;
import app.mrquan.server.impl.AdminServer;
import app.mrquan.server.impl.LoginServer;
import app.mrquan.server.impl.MessageServer;

public class Factory {
    public static final int SERVER_LOGIN = 0;
    public static final int SERVER_MESSAGE = 1;
    public static final int SERVER_ADMIN = 2;

    static public IServer getIServerInstance(int type, ServerRoom serverRoom){
        switch (type){
            case SERVER_LOGIN:
                return new LoginServer(serverRoom);
            case SERVER_MESSAGE:
                return new MessageServer(serverRoom);
            case SERVER_ADMIN:
                return new AdminServer(serverRoom);
            default:
                return null;
        }
    }
}
