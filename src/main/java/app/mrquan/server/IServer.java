package app.mrquan.server;

import java.util.concurrent.ExecutorService;

public interface IServer extends Runnable{
    void finish();
    int getThreadCount();
}
