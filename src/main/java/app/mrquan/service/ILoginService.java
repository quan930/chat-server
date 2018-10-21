package app.mrquan.service;

import app.mrquan.pojo.Instruct;
import app.mrquan.pojo.User;

public interface ILoginService {
    /**
     * 用户登录
     * @param json 用户对象
     * @param address 要连接的 ip 地址
     * @param port 要连接的端口号
     * @return Instruct 指令对象json
     */
    String login(String json,String address,int port);
}
