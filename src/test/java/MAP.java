import app.mrquan.pojo.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MAP {
    public static void main(String[] args) {
//        Map<String, List<String>> messageMap = new HashMap<String, List<String>>();
//        List<String> strings = messageMap.get("0001");
//        System.out.println(strings);
//        List<String> strings1 = new ArrayList<String>();
//        messageMap.put("0001",strings1);
//        List<String> string1 = messageMap.get("0001");
//        System.out.println(string1);
//        messageMap.get("0001").add("aaaaa");
//        messageMap.get("0001").add("asddd");
//        List<String> string2 = messageMap.get("0001");
//        System.out.println(string2);



        ConcurrentHashMap<String, CopyOnWriteArrayList<Message>> map = new ConcurrentHashMap<>();
        map.put("0001",new CopyOnWriteArrayList<Message>());
//        System.out.println(map.containsKey("0001"));
//        Message message = new Message();
//        message.setSendObject("0001");
//        message.setDate(new Date());
//        message.setUser("0002");
//        message.setNews("hello");
//        boolean b = map.get("0001").add(message);
//        System.out.println(b);
////        map.get("0001");
//        for (Message message1:map.get("0001")){
//            System.out.println(message1);
//        }
        map.remove("0002");
    }
}
