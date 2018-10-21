package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Instruct;
import app.mrquan.pojo.User;
import app.mrquan.service.ILoginService;
import app.mrquan.util.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class LoginServiceImpl implements ILoginService {
    public String login(String json, String address, int port) {
        Instruct instruct = new Instruct();
        User user = JSON.jsonToUser(json);
        if (user==null){
            instruct.setInstruct("error");
        }else {
            /**
             * 查询数据库 user
             */
            User userDAO = DAOFactory.getIUserDAOInstance().selectByID(user.getId());
            if (userDAO==null){
                instruct.setInstruct("user not exist");
            }else if (!user.getPassword().equals(userDAO.getPassword())){
                instruct.setInstruct("password error");
            }else {
                /**
                 * 查询令牌
                 */
                int m = DAOFactory.getITokenDAOInstance().add(user.getId());
                if (m==0){
                    instruct.setInstruct("login duplicate");
                }else {
                    instruct.setInstruct("success");
                }
                instruct.setAddress(address);
                instruct.setPort(port);
            }
        }
        //令牌转json
        return JSON.instructToJson(instruct);
    }
}
