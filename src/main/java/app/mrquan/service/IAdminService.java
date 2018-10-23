package app.mrquan.service;

import app.mrquan.server.IServer;
import app.mrquan.server.impl.AdminServer;
import app.mrquan.server.impl.LoginServer;
import app.mrquan.server.impl.MessageServer;

public interface IAdminService {
    /**
     * 管理员登录
     * @param json 用户对象
     * @return Instruct 指令对象json
     */
    String login (String json);

    /**
     * 管理员任务
     * @param json Function json 格式
     * @param loginServer 登陆服务器对象
     * @param adminServer   管理员服务器对象
     * @param messageServer 消息服务器对象
     * @return 成功返回json数据 否则返回null
     */
    String function(String json, IServer loginServer, IServer adminServer, IServer messageServer);
}
