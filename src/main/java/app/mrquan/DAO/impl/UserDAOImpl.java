package app.mrquan.DAO.impl;

import app.mrquan.DAO.IUserDAO;
import app.mrquan.pojo.User;
import app.mrquan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    public User selectByID(String id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "select * from users where id = ?";
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getString(1));//id
                user.setPassword(resultSet.getString(2));//password
                resultSet.getString(3);//admin
                if (!resultSet.wasNull()){
                    user.setAdmin(Boolean.TRUE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "select * from users";
        List<User> users = new ArrayList<>();
        try {
            con = DBUtil.createConnection();
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getString(1));//id
                user.setPassword(resultSet.getString(2));//password
                resultSet.getString(3);//admin
                if (!resultSet.wasNull()){
                    user.setAdmin(Boolean.TRUE);
                }else {
                    user.setAdmin(Boolean.FALSE);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,preparedStatement,resultSet);
        }
        return users;
    }
}
