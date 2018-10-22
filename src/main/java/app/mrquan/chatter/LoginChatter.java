package app.mrquan.chatter;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Instruct;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class LoginChatter implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Integer messagePort;
    private String messageAddress;

    public LoginChatter(Socket client,String messageAddress,Integer messagePort){
        this.client = client;
        this.messageAddress = messageAddress;
        this.messagePort = messagePort;
    }
    public void run() {
        Instruct instruct = new Instruct();//指令类
        instruct.setInstruct("error");//登录状况
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),StandardCharsets.UTF_8));
            String jsonLogin = reader.readLine();//读登录字符串
            /**
             * 处理json 解析为User 对象
             * 判断用户存在 密码是否正确 判断是否已经登陆
             * 将 instruct 类转为 json数据
             */
            String json = ServiceFactory.getILoginServiceInstance().login(jsonLogin,messageAddress,messagePort);
            writer.write(json);
            writer.newLine();
            writer.flush();
        } catch (SocketException e){
            System.out.println("登陆套节字关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                writer.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}