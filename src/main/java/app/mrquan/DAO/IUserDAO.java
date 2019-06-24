package app.mrquan.DAO;

import app.mrquan.pojo.User;

import java.util.List;

public interface IUserDAO {
    /**
     * 根据id 查询用户
     * @param id 用户id
     * @return 用户对象
     */
    User selectByID(String id);

    /**
     * 查询全部用户
     * @return 用户列表
     */
    List<User> selectAll();
}
