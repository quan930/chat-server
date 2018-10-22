package app.mrquan.server.chatter;

import app.mrquan.factory.ServiceFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class MessageWriterChatter implements Runnable {
    private Socket client;
    private BufferedWriter writer;
    private String user;

    public MessageWriterChatter(Socket client,BufferedWriter writer,String user){
        this.client = client;
        this.writer = writer;
        this.user = user;
    }

    @Override
    public void run() {
        while (!client.isClosed()){
            try {
                List<String> strings = ServiceFactory.getIMessageServiceInstance().getMessage(user);
                if (strings!=null){
                    for (String s:strings){
                        writer.write(s);
                        writer.newLine();
                        writer.flush();
                    }
                }
                Thread.sleep(1000);
            } catch (SocketException e) {
//                System.out.println("写入套节字关闭");
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
//                System.out.println("线程错误");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
