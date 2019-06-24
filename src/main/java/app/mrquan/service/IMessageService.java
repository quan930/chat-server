package app.mrquan.service;

import java.util.List;

public interface IMessageService {
    /**
     * 登陆 判断令牌是否存在 否则 失败返回连接登陆服务器指令
     * @param json 用户对象
     * @param address 失败后要连接的 登陆服务器 ip地址
     * @param port 失败后要连接的 登陆服务器 端口号
     * @return Instruct 指令对象json
     */
    String login (String json,String address,int port);

    /**
     * 返回用户 id
     * @param json 用户对象
     * @return 成功返回 ID 否则返回null
     */
    String getUser(String json);

    /**
     * 消息处理
     * @param json 消息类的json 数据
     * @return 处理成功返回1 否则返回0 退出登陆返回-1
     */
    int message (String json);

    /**
     * 获得消息队列
     * @param user 用户id
     * @return 返回消息队列
     */
    List<String> getMessage(String user);

    /**
     * 获得全部用户
     * @return list 用户列表 json
     */
    String getAllUsers();
}
