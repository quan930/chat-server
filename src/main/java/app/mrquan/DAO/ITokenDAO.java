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
}