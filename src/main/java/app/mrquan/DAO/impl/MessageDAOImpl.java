package app.mrquan.DAO.impl;

import app.mrquan.DAO.IMessageDAO;
import app.mrquan.pojo.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageDAOImpl implements IMessageDAO {
    static private ConcurrentHashMap<String, CopyOnWriteArrayList<Message>> map = new ConcurrentHashMap<>();
    public int put(Message message) {//需要锁？
        if (!map.containsKey(message.getSendObject())){//不包含
            map.put(message.getSendObject(),new CopyOnWriteArrayList<Message>());
        }
        return (map.get(message.getSendObject()).add(message)==true ? 1:0);
    }

    public List<Message> get(String id) {
        return map.remove(id);
    }

    @Override
    public void release() {
        return;
    }
}
