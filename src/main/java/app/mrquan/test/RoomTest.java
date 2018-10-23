package app.mrquan.test;

import app.mrquan.control.ServerRoom;

import java.util.Scanner;

public class RoomTest {
    public static void main(String[] args) {
        new ServerRoom("127.0.0.1",7002, 7003,7001,"/Users/daquan/Desktop/token.data","/Users/daquan/Desktop/message.data").start();
    }
}