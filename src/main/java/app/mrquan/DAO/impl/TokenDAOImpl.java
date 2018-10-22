package app.mrquan.DAO.impl;

import app.mrquan.DAO.ITokenDAO;

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
}
