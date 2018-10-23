package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Function;
import app.mrquan.pojo.Instruct;
import app.mrquan.pojo.User;
import app.mrquan.server.IServer;
import app.mrquan.server.impl.AdminServer;
import app.mrquan.server.impl.LoginServer;
import app.mrquan.server.impl.MessageServer;
import app.mrquan.service.IAdminService;
import app.mrquan.util.JSON;

public class AdminServiceImpl implements IAdminService {
    @Override
    public String login(String json) {
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
            }else if (!Boolean.TRUE.equals(userDAO.getAdmin())){
                instruct.setInstruct("not Admin");
            }else {
                instruct.setInstruct("success");
            }
        }
        return JSON.instructToJson(instruct);
    }

    @Override
    public String function(String json, IServer loginServer, IServer adminServer, IServer messageServer) {
        Function function = JSON.jsonToFunction(json);
        StringBuilder stringBuilder;
        if (function==null)
            return null;
        if (function.getFunction()==null)
            return null;
        switch (function.getFunction()){
            case "thread count":
                //线程数量
                stringBuilder = new StringBuilder();
                stringBuilder.append("loginServer ThreadCount:");
                stringBuilder.append(loginServer.getThreadCount());
                stringBuilder.append("adminServer ThreadCount:");
                stringBuilder.append(adminServer.getThreadCount());
                stringBuilder.append("messageServer ThreadCount:");
                stringBuilder.append(messageServer.getThreadCount());
                function.setDescription(stringBuilder.toString());
                break;
            case "online number":
                //上线人数
                function.setDescription(String.valueOf(DAOFactory.getITokenDAOInstance().size()));
                break;
            case "quit":
                function.setDescription("Bye!");
                //退出
                break;
            case "shut down":
                //结束服务器
                loginServer.finish();
                messageServer.finish();
                adminServer.finish();
                break;
            default:
                function = null;
                break;
        }
        return JSON.functionToJson(function);
    }
}
