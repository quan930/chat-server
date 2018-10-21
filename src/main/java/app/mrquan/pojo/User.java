package app.mrquan.pojo;

public class User {//用户登陆类
    private String id;
    private String password;

    @Override
    public String toString() {
        return "微信号:"+id+"\t密码:"+password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
