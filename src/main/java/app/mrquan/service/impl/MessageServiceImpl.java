package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Instruct;
import app.mrquan.pojo.Message;
import app.mrquan.pojo.User;
import app.mrquan.service.IMessageService;
import app.mrquan.util.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageServiceImpl implements IMessageService {
    public String login(String json, String address, int port) {
        Instruct instruct = new Instruct();
        User user = JSON.jsonToUser(json);
        if (user==null)
            return null;
        int m = DAOFactory.getITokenDAOInstance().contains(user.getId());
        if (m==1){
            instruct.setInstruct("success");
        }else {
            instruct.setInstruct("No landing");
            instruct.setAddress(address);
            instruct.setPort(port);
        }
        return JSON.instructToJson(instruct);
    }

    @Override
    public String getUser(String json) {
        User user = JSON.jsonToUser(json);
        if (user==null)
            return null;
        return user.getId();
    }

    public int message(String json) {
        Message message = JSON.jsonToMessage(json);
        if (message==null)
            return 0;
        if (message.getFunction()==null)
            return 0;
        if (message.getUser()==null)
            return 0;
        int remove = 0;
        switch (message.getFunction()){
            case "exit":
                DAOFactory.getITokenDAOInstance().remove(message.getUser());
                remove = 0;
                break;
            case "chat":
                remove = DAOFactory.getIMessageDAOInstance().put(message);
                break;
            default:
                break;
        }
        return remove;
    }

    @Override
    public List<String> getMessage(String user) {
        List<String> strings = new ArrayList<>();
        List<Message> messages = DAOFactory.getIMessageDAOInstance().get(user);
        if (messages==null)
            return null;
        String string;
        for (Message s:messages){
            string = JSON.messageToJson(s);
            if (string==null)
                return null;
            strings.add(string);
        }
        return strings;
    }

    @Override
    public String getAllUsers() {
        List<String> users = new ArrayList<>();
        for (User user:DAOFactory.getIUserDAOInstance().selectAll()){
            if (!user.getAdmin())
                users.add(user.getId());
        }
        return JSON.userList(users);
    }
}
