package app.mrquan.server.chatter;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Instruct;
import app.mrquan.server.IServer;
import app.mrquan.server.impl.AdminServer;
import app.mrquan.server.impl.LoginServer;
import app.mrquan.server.impl.MessageServer;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class AdminChatter implements Runnable {
    private IServer adminServer;
    private IServer loginServer;
    private IServer messageServer;
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;


    public AdminChatter(Socket client, IServer adminServer, IServer loginServer, IServer messageServer){
        this.client = client;
        this.adminServer = adminServer;
        this.loginServer = loginServer;
        this.messageServer = messageServer;
    }
    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),StandardCharsets.UTF_8));
            String jsonLogin = reader.readLine();
            String result = ServiceFactory.getIAdminServiceInstance().login(jsonLogin);
            if (result==null){
                close();
                return;
            }
            writer.write(result);
            writer.newLine();
            writer.flush();
            if (!result.contains("success")){
                close();
                return;
            }
            String json;
            String resultJson;
            while (true){
                json = reader.readLine();
                resultJson = ServiceFactory.getIAdminServiceInstance().function(json,loginServer,adminServer,messageServer);
                if (resultJson==null){
                    break;
                }
                writer.write(resultJson);
                writer.newLine();
                writer.flush();
                if (resultJson.contains("Bye")){
                    break;
                }
            }
        } catch (SocketException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close(){
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
