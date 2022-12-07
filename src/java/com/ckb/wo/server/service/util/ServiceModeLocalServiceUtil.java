package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.ServiceMode;
import com.ckb.wo.client.model.ServiceModeExample;
import com.ckb.wo.server.service.IServiceModeService;

public class ServiceModeLocalServiceUtil extends GenericServiceUtil {

    public static int countServiceMode() {
        return getServiceModeService().countServiceMode();
    }

    public static List<ServiceMode> getServiceMode() {
        return getServiceModeService().getServiceMode();
    }

    public static List<ServiceMode> getServiceModeByExample(ServiceModeExample example) {
        return getServiceModeService().getServiceModeByExample(example);
    }

    public static ServiceMode getServiceModeByPrimaryKey(Long servicemodePk) {
        return getServiceModeService().getServiceModeByPrimaryKey(servicemodePk);
    }

    public static int countServiceModeByExample(ServiceModeExample example) {
        return getServiceModeService().countServiceModeByExample(example);
    }

    public static int deleteServiceModeByPrimaryKey(Long servicemodePk) {
        return getServiceModeService().deleteServiceMode(servicemodePk);
    }

    public static int updateServiceMode(ServiceMode servicemode) {
        return getServiceModeService().updateServiceMode(servicemode);
    }

    private static IServiceModeService getServiceModeService() {
        return (IServiceModeService) getBean("servicemodeService");
    }

    public static Long insertServiceMode(ServiceMode servicemode) {
        return (Long) getServiceModeService().insertServiceMode(servicemode);
    }
}
