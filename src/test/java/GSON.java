import app.mrquan.pojo.Message;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Date;

public class GSON {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Message message = new Message();
        message.setUser("0001");
        message.setSendObject("0002");
        message.setNews("你好");
        message.setDate(new Date());
        System.out.println(message);
        String json = gson.toJson(message,Message.class);
        System.out.println(json);

        Message message1 = gson.fromJson(json+"\n",Message.class);
        System.out.println(message1);
//        Message message = gson.fromJson("aaaa",Message.class);
//        try {
//            Message message = gson.fromJson("aaaa",Message.class);
//            System.out.println(message);
//            return;
//        }catch (JsonSyntaxException e){
//            System.out.println("asddddd");
//        }finally {
//            System.out.println("asdadas");
//        }
    }
}