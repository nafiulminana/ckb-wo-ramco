package com.ckb.wo.server.service.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Admin
 */
public class BeanFactory {

    private static ApplicationContext applicationContext;

    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param aApplicationContext the applicationContext to set
     */
    public static void setApplicationContext(ApplicationContext aApplicationContext) {
        applicationContext = aApplicationContext;
    }


    public static Object getBean(String beanName){
        if(applicationContext==null){
            applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        }
        return getApplicationContext().getBean(beanName);
    }
}
