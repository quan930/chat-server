package app.mrquan.DAO.impl;

import app.mrquan.DAO.ITokenDAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TokenDAOImpl implements ITokenDAO {
    private static BlockingQueue<String> tokenQueue= new PriorityBlockingQueue<String>();
    public int add(String token) {
        if (tokenQueue.contains(token)){//重复判断
            return 0;
        }else {
            try {
                tokenQueue.put(token);//阻塞
                return 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public int remove(String token) {
        if (tokenQueue.remove(token)){
            return 1;
        }
        return 0;
    }

    public int contains(String token) {
        if (tokenQueue.contains(token)){
            return 1;
        }
        return 0;
    }

    @Override
    public int size() {
        return tokenQueue.size();
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
            List<String> strings = new LinkedList<>(tokenQueue);
            for (String string : strings) {
                out.writeObject(string);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        tokenQueue.clear();
        return 1;
    }

}
