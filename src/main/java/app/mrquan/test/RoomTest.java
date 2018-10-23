package app.mrquan.test;

import app.mrquan.control.ServerRoom;

import java.util.Scanner;

public class RoomTest {
    public static void main(String[] args) {
//        ServerRoom serverRoom = new ServerRoom("127.0.0.1",7002, 7003,7001);
//        serverRoom.start();
//        Scanner in = new Scanner(System.in);
//        in.nextInt();
//        serverRoom.getLoginServer().finish();
//        serverRoom.getMessageServer().finish();

        new ServerRoom("127.0.0.1",7002, 7003,7001).start();
    }
}