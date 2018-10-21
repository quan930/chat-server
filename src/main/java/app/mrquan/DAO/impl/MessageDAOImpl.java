package app.mrquan.DAO.impl;

import app.mrquan.DAO.IMessageDAO;
import app.mrquan.pojo.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDAOImpl implements IMessageDAO {
    private static Map<String,List<Message>> messageMap = new HashMap<String, List<Message>>();
    public int put(Message message) {//需要锁？
        /**
         * 判断接收者是否有队列
         * 没有创建队列
         */
        boolean yOrN = false;
        synchronized (this){
            if (messageMap.get(message.getSendObject())==null){
                messageMap.put(message.getSendObject(),new ArrayList<Message>());
            }
            yOrN = messageMap.get(message.getSendObject()).add(message);
        }
        if (yOrN)
            return 1;
        return 0;
    }

    public List<Message> get(String id) {
        synchronized (this){
            return messageMap.remove(id);
        }
    }
}
