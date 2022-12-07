/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.listener;

import javax.servlet.ServletContext;

/**
 *
 * @author Amaran Sidhiq
 */
public class ServletContextInfo {
    static ServletContext context=null;
    public static String getContextPath(){
        return context.getContextPath();
    }
    public static String getRealPath(String path){
        return context.getRealPath(path);
    }
    public static String getParameter(String param){
        return context.getInitParameter(param);
    }
    public static void setServletContex(ServletContext scontext){
        context=scontext;
    }
    public static ServletContext getServletContex(){
        return context;
    }
}
