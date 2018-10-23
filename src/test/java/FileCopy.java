import java.io.*;

public class FileCopy {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("/Users/daquan/Desktop/token.data");
        System.out.println(file.exists());
        if (file.exists()){
            file.delete();
        }else {
            file.createNewFile();
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject("hello");
        out.flush();
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        String a = (String) in.readObject();
        System.out.println(a);
    }
}
