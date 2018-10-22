package app.mrquan.server;

public interface IServer extends Runnable{
    void finish();
    int getThreadCount();
}