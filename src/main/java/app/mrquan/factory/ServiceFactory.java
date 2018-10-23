package app.mrquan.factory;

import app.mrquan.service.IAdminService;
import app.mrquan.service.ILoginService;
import app.mrquan.service.IMessageService;
import app.mrquan.service.impl.AdminServiceImpl;
import app.mrquan.service.impl.LoginServiceImpl;
import app.mrquan.service.impl.MessageServiceImpl;

public class ServiceFactory {
    public static ILoginService getILoginServiceInstance(){
        return new LoginServiceImpl();
    }

    public static IMessageService getIMessageServiceInstance(){
        return new MessageServiceImpl();
    }

    public static IAdminService getIAdminServiceInstance(){
        return new AdminServiceImpl();
    }
}
