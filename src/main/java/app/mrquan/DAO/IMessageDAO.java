package app.mrquan.DAO;

import app.mrquan.pojo.Message;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IMessageDAO {
    /**
     * 加数据
     * @param message 消息对象
     * @return 成功返回1 否则返回0
     */
    int put(Message message);

    /**
     * 取得数据
     * @param id 用户id
     * @return 有数据返回List 没有返回null
     */
    List<Message> get(String id);

    /**
     * 保存数据
     * @param path 文件路径
     * @return 成功返回1 否则返回0
     */
    int save(String path);

    /**
     * 初始化
     * @param path 文件路径
     */
    void init(String path);
}
