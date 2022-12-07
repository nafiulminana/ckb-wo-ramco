/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.listener;

import com.ckb.wo.server.service.constant.WOConstant;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author Admin
 */
public class GenericMasterTableServlet extends HttpServlet {


    protected String getPageNumber(HttpServletRequest request, HttpServletResponse response, String tableId){

        String paramPageNumber = (new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
        String pageNumber=null;
        if(paramPageNumber==null ){
            pageNumber="0";
        }else{
            pageNumber = request.getParameter(paramPageNumber);
            if(pageNumber==null || "1".equals(pageNumber)){
                pageNumber="0";
            }
        }
        return pageNumber;
    }

    protected String getOrderBy(HttpServletRequest request, HttpServletResponse response, String tableId){

        if(getSortBy(request, response, tableId)==null){
            return null;
        }
        else{
            return getSortBy(request, response, tableId)+" "+getAscDesc(request, response, tableId);
        }
    }

    protected String getSortBy(HttpServletRequest request, HttpServletResponse response, String tableId){
        String paramName = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT);
        return request.getParameter(paramName);
    }

    protected String getAscDesc(HttpServletRequest request, HttpServletResponse response, String tableId){
        String paramName = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
        String ascDesc = request.getParameter(paramName);
        if(ascDesc!=null){
            if("1".equalsIgnoreCase(ascDesc)){
                return WOConstant.ASC;
            }else{
                return WOConstant.DESC;
            }
        }
        return null;
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
