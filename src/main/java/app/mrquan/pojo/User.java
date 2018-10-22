package app.mrquan.pojo;

public class User {//用户登陆类
    private String id;
    private String password;
    private Boolean admin;//管理员

    @Override
    public String toString() {
        return "微信号:"+id+"\t密码:"+password+"\t"+((Boolean.TRUE.equals(admin))?"管理员":"用户");
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
