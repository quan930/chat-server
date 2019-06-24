package app.mrquan.util;

import app.mrquan.pojo.Function;
import app.mrquan.pojo.Instruct;
import app.mrquan.pojo.Message;
import app.mrquan.pojo.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Date;
import java.util.List;

public class JSON {
    public static String messageToJson(Message message){
        //判断null
        if (message==null)
            return null;
//        if (message.getSender()==null)
//            return null;
//        if (message.getNews()==null)
//            return null;
//        if (message.getReceiver()==null)
//            return null;
//        if (message.getDate()==null)
//            return null;
        return new Gson().toJson(message,Message.class);
    }

    public static Message jsonToMessage(String json){
        Message message = null;
        try {
            message = new Gson().fromJson(json,Message.class);
        }catch (JsonSyntaxException ignored){
        }
        //判断null
        if (message==null)
            return null;
        if (message.getFunction()==null)
            return null;
        if (message.getUser()==null)
            return null;
        if (message.getDate()==null)
            message.setDate(new Date());
        return message;
    }
    public static User jsonToUser(String json){
        User user = null;
        try {
            user = new Gson().fromJson(json,User.class);
        }catch (JsonSyntaxException ignored){
        }
        if (user==null)
            return null;
        if (user.getPassword()==null)
            return null;
        if (user.getId()==null)
            return null;
        return user;
    }
    public static String instructToJson(Instruct instruct){
        //判断null
        if (instruct==null)
            return null;
        if (instruct.getInstruct()==null)
            return null;
        return new Gson().toJson(instruct,Instruct.class);
    }

    public static Function jsonToFunction(String json){
        Function function = null;
        try {
            function = new Gson().fromJson(json,Function.class);
        }catch (JsonSyntaxException ignored){
        }
        if (function==null)
            return null;
        if (function.getFunction()==null)
            return null;
        return function;
    }

    public static String functionToJson(Function function){
        if (function==null)
            return null;
        if (function.getFunction()==null)
            return null;
        return new Gson().toJson(function,Function.class);
    }

    /**
     * 用户列表转json
     * @param list 用户列表
     * @return
     */
    public static String userList(List<String> list){
        return new Gson().toJson(list);
    }
}
