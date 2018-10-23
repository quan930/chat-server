package app.mrquan.test;

import app.mrquan.control.ServerRoom;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ServerRoom serverRoom = new ServerRoom("127.0.0.1",7002,
                7003,7001,"/Users/daquan/Desktop/token.data","/Users/daquan/Desktop/message.data");
        serverRoom.start();
        Scanner in = new Scanner(System.in);
        int i;
        while ((i = in.nextInt())!=-1){
            switch (i){
                case 1:
                    System.out.println("11111111");
                    serverRoom.getLoginServer().finish();
                    break;
                case 2:
                    System.out.println("22222222");
                    serverRoom.getMessageServer().finish();
                    break;
                case 3:
                    System.out.println("33333333");
                    serverRoom.getAdminServer().finish();
                    break;
                case 4:
                    int threadCount = serverRoom.getLoginServer().getThreadCount();
                    System.out.println("登录池线程活跃数:"+serverRoom.getLoginServer().getThreadCount()+"\t消息池线程活跃数:"+serverRoom.getMessageServer().getThreadCount());
            }
        }
    }
}
