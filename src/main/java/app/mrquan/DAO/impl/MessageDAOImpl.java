package app.mrquan.DAO.impl;

import app.mrquan.DAO.IMessageDAO;
import app.mrquan.pojo.Message;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageDAOImpl implements IMessageDAO {
    static private ConcurrentHashMap<String, CopyOnWriteArrayList<Message>> map = new ConcurrentHashMap<>();
    public int put(Message message) {
        if (!map.containsKey(message.getSendObject())){//不包含
            map.put(message.getSendObject(),new CopyOnWriteArrayList<Message>());
        }
        return (map.get(message.getSendObject()).add(message)==true ? 1:0);
    }

    public List<Message> get(String id) {
        if (!map.containsKey(id))
            return null;
        return map.remove(id);
    }

    @Override
    public int save(String path) {
        try {
            File file = new File(path);
            if (file.exists()){
                file.delete();
            }else {
                file.createNewFile();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            for (CopyOnWriteArrayList<Message> value : map.values()) {
                for (Message aValue : value) {
                    out.writeObject(aValue);
                }
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        map.clear();
        return 1;
    }

    @Override
    public void init(String path) {
        File file = new File(path);
        if (!file.exists())
            return;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            while (true){
                Message message = (Message) in.readObject();
                if (message==null){
                    break;
                }
                put(message);
            }
        }catch (EOFException ignored){

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        file.delete();
    }
}