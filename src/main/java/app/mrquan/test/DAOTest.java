package app.mrquan.test;

import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.Factory;
import app.mrquan.pojo.Message;
import app.mrquan.pojo.User;
import app.mrquan.util.JSON;

import java.util.List;

public class DAOTest {
    public static void main(String[] args) {
//        System.out.println(DAOFactory.getITokenDAOInstance().add("qwe"));
//        System.out.println(DAOFactory.getITokenDAOInstance().add("1"));
//        System.out.println(DAOFactory.getITokenDAOInstance().add("2"));
//        System.out.println(DAOFactory.getITokenDAOInstance().add("1"));
//        System.out.println(DAOFactory.getITokenDAOInstance().add("qwe"));
//        System.out.println(DAOFactory.getITokenDAOInstance().contains("qw"));
//        System.out.println(DAOFactory.getITokenDAOInstance().remove("qw"));


//        User user = DAOFactory.getIUserDAOInstance().selectByID("0001");
//        System.out.println(user);

//        List<Message> messages;
//        messages = DAOFactory.getIMessageDAOInstance().get("0001");
//        System.out.println(messages);
//        Message message = new Message();
////        message.setReceiver("0001");
//        int m = DAOFactory.getIMessageDAOInstance().put(message);
//        System.out.println(m);
//        m = DAOFactory.getIMessageDAOInstance().put(message);
//        System.out.println(m);
//        messages = DAOFactory.getIMessageDAOInstance().get("0001");
//        System.out.println(messages);
//        messages = DAOFactory.getIMessageDAOInstance().get("0001");
//        System.out.println(messages);


        Message message = new Message();
        message.setUser("0001");
        message.setFunction("退出");
        String json = JSON.messageToJson(message);
        System.out.println(json);
        Message message1 = JSON.jsonToMessage(json);
        System.out.println(message1);


    }
}
