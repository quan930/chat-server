package app.mrquan.DAO;

import app.mrquan.pojo.User;

public interface IUserDAO {
    User selectByID(String id);
}
