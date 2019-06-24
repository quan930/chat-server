package app.mrquan.pojo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String user;//用户
    private String sendObject;//发送对象
    private String news;//消息
    private Date date;
    private String function;//功能

    @Override
    public String toString() {
        return "功能:"+function+"\t\t用户:"+user+"\t\t发送对象:"+sendObject+"\t\t消息:"+news+"\t\t时间:"+date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
