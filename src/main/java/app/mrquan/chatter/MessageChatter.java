package app.mrquan.chatter;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Instruct;
import app.mrquan.util.JSON;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MessageChatter implements Runnable{
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Integer loginPort;
    private String loginAddress;
    private Timer timer;
    private String user;

    public MessageChatter(Socket client, String loginAddress, Integer loginPort){
        this.client = client;
        this.loginAddress = loginAddress;
        this.loginPort = loginPort;
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),StandardCharsets.UTF_8));
            String json = reader.readLine();
            String result = ServiceFactory.getIMessageServiceInstance().login(json,loginAddress,loginPort);
            if (result==null){
                return;
            }
            writer.write(result);
            writer.newLine();
            writer.flush();
            if (!result.contains("success")){
                return;
            }
            user = ServiceFactory.getIMessageServiceInstance().getUser(json);
            writerMessage();//写入线程
            String jsonMessage;
            int resultMessage;
            while (true){
                jsonMessage = reader.readLine();
                resultMessage = ServiceFactory.getIMessageServiceInstance().message(jsonMessage);
                if (resultMessage==0){//失败断开连接
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                reader.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writerMessage(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<String> strings = ServiceFactory.getIMessageServiceInstance().getMessage(user);
                if (strings!=null){
                    for (String s:strings){
                        try {
                            writer.write(s);
                            writer.newLine();
                            writer.flush();
                        } catch (IOException e) {
                            timer.cancel();
                            e.printStackTrace();
                        }
                    }
                }
            }
        },0,2000);
    }
}