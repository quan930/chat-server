import app.mrquan.pojo.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MAP {
    public static void main(String[] args) {
        Map<String, List<String>> messageMap = new HashMap<String, List<String>>();
        List<String> strings = messageMap.get("0001");
        System.out.println(strings);
        List<String> strings1 = new ArrayList<String>();
        messageMap.put("0001",strings1);
        List<String> string1 = messageMap.get("0001");
        System.out.println(string1);
        messageMap.get("0001").add("aaaaa");
        messageMap.get("0001").add("asddd");
        List<String> string2 = messageMap.get("0001");
        System.out.println(string2);
    }
}
