import app.mrquan.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONFactory;

import java.io.IOException;

public class JSON {
    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setId("123");
        user.setPassword("ps123");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
        String mm = "{\"id\":\"123\",\"password\":null}";
        User user1 = mapper.readValue(mm, User.class);
        System.out.println(user1);
        System.out.println(mm.contains("123"));
    }
    static private void json(){//org.json
//        String json = "{\"name\":\"quan\",\"age\":18}";
//        JSONObject jsonObject = new JSONObject(json);
//        String name = jsonObject.getString("name");
//        Integer age = jsonObject.getInt("age");
//        System.out.println(name);
//        System.out.println(age);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("name","quan");
//        jsonObject1.put("age",13);
////        jsonObject1.toString();
//        System.out.println(jsonObject1);
    }
}