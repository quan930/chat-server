package app.mrquan.factory;

import app.mrquan.service.ILoginService;
import app.mrquan.service.IMessageService;
import app.mrquan.service.impl.LoginServiceImpl;
import app.mrquan.service.impl.MessageServiceImpl;

public class ServiceFactory {
    public static ILoginService getILoginServiceInstance(){
        return new LoginServiceImpl();
    }

    public static IMessageService getIMessageServiceInstance(){
        return new MessageServiceImpl();
    }
}
