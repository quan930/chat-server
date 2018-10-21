package app.mrquan.factory;

import app.mrquan.DAO.IMessageDAO;
import app.mrquan.DAO.ITokenDAO;
import app.mrquan.DAO.IUserDAO;
import app.mrquan.DAO.impl.MessageDAOImpl;
import app.mrquan.DAO.impl.TokenDAOImpl;
import app.mrquan.DAO.impl.UserDAOImpl;

public class DAOFactory {
    static public ITokenDAO getITokenDAOInstance(){
        return new TokenDAOImpl();
    }

    static public IUserDAO getIUserDAOInstance(){
        return new UserDAOImpl();
    }

    static public IMessageDAO getIMessageDAOInstance(){
        return new MessageDAOImpl();
    }
}
