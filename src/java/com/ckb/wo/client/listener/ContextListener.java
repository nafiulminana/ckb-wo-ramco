/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import com.ckb.server.cron.ReportOPS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Amaran Sidhiq
 */
public class ContextListener implements ServletContextListener {
//    Timer timer = null;
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextInfo.setServletContex(sce.getServletContext());
//
//        String ctime = ServletContextInfo.getServletContex().getInitParameter("crontime");
//        Long interval = new Long(ServletContextInfo.getServletContex().getInitParameter("croninterval"));
//        interval = interval * 60 * 1000;
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//        Calendar t = Calendar.getInstance();
//        try {
//            t.setTime(sdf.parse(ctime));
//        } catch (ParseException ex) {
//            logger.LoggerClass.logError(ex);
//        }
//        final Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, 1);
//        c.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
//        c.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
//
//        t=null;
//        timer=new Timer();
//        TimerTask tt = new TimerTask() {
//
//            @Override
//            public void run() {
//                Calendar now = Calendar.getInstance();
//                System.out.println("["+now.getTime().toString()+"] Checking Scheduled Task");
//                if(now.after(c)){
//                    ReportOPS.main(null);
//                    c.add(Calendar.DATE, 1);
//                }
//                System.out.println("Next Execution: "+ c.getTime().toString());
//            }
//        };
//        timer.scheduleAtFixedRate(tt, 0,interval);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextInfo.setServletContex(null);
//        if(timer !=null){
//            timer.cancel();
//        }
    }

}
