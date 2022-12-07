/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class SessionFilter implements Filter {

    private ArrayList<String> urlList;
    private int totalURLS;
    
    public SessionFilter() {
    } 
  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse res,
          FilterChain chain) throws IOException, ServletException {

      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) res;
      String url = request.getServletPath();
      boolean allowedRequest = false;

      for(int i=0; i<totalURLS; i++) {
          if(url.contains(urlList.get(i))) {
              allowedRequest = true;
              break;
          }
      }

      
          if (!allowedRequest) {
              HttpSession session = request.getSession(false);
              if (null == session  ) {
                  //request.getRequestDispatcher("index.jsp").forward(req, res);
                  response.sendRedirect(request.getContextPath() + "/index.jsp");
                  return;
              }
              else{
                   Object loginInfo = session.getAttribute("loginInfo");
                   if(loginInfo==null){
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    return;
                   }
              }
          }
        
      chain.doFilter(req, res);
  }

  public void init(FilterConfig config) throws ServletException {
      String urls = config.getInitParameter("avoid-urls");
      StringTokenizer token = new StringTokenizer(urls, ",");

      urlList = new ArrayList<String>();

      while (token.hasMoreTokens()) {
          urlList.add(token.nextToken());

      }
      totalURLS = urlList.size();
  }
}
