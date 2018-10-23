package app.mrquan.DAO;

public interface ITokenDAO {
    /**
     * 加入令牌
     * @param token 账户id
     * @return 成功返回1 否则返回0
     */
    int add(String token);

    /**
     * 删除令牌
     * @param token 账户id
     * @return 成功返回1 否则返回0
     */
    int remove(String token);

    /**
     * 查找令牌
     * @param token 账户id
     * @return 存在返回1 否则返回0
     */
    int contains(String token);

    /**
     * 查询令牌数量
     * @return 返回令牌数量 没有返回0
     */
    int size();

    /**
     * 保存数据
     * @param path 文件路径
     * @return 成功返回1 否则返回0
     */
    int save(String path);
}