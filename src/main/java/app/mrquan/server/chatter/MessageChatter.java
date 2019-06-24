package app.mrquan.server.chatter;

import app.mrquan.factory.ServiceFactory;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

public class MessageChatter implements Runnable{
    private ExecutorService cachedThreadPool;
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Integer loginPort;
    private String address;
    private String user;

    public MessageChatter(Socket client, String address, Integer loginPort,ExecutorService cachedThreadPool){
        this.client = client;
        this.address = address;
        this.loginPort = loginPort;
        this.cachedThreadPool = cachedThreadPool;
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),StandardCharsets.UTF_8));
            String json = reader.readLine();
            String result = ServiceFactory.getIMessageServiceInstance().login(json,address,loginPort);
            if (result==null){
                closeAll();
                return;
            }
            writer.write(result);//发送登陆返回码
            writer.newLine();
            writer.flush();
            if (!result.contains("success")){
                closeAll();
                return;
            }
            writer.write(ServiceFactory.getIMessageServiceInstance().getAllUsers());//发送登陆返回码
            writer.newLine();
            writer.flush();
            user = ServiceFactory.getIMessageServiceInstance().getUser(json);
        }catch (SocketException e){
//            System.out.println("消息套节字关闭");
            closeAll();
            return;
        }catch (IOException e) {
            e.printStackTrace();
            closeAll();
            return;
        }
        cachedThreadPool.execute(new MessageWriterChatter(client,writer,user));
        try {
            String jsonMessage;
            int resultMessage;
            while (true){
                jsonMessage = reader.readLine();
                resultMessage = ServiceFactory.getIMessageServiceInstance().message(jsonMessage);
                if (resultMessage==0){//失败断开连接
                    break;
                }
            }
        } catch (SocketException e) {
//            System.out.println("messageChatter关闭");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void closeAll(){
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(){
        try {
            reader.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}