/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.server.service.util;

import com.ckb.wo.server.service.constant.WOConstant;

/**
 *
 * @author Admin
 */
public class PagingUtil {

    /*
     * pageNumber before converted to mysql pagenumber, i.e
     * always start from 1
     */
    private static int countOffset(int totalRecord, int pageNumberCannotBeZero){
        // pageNumber is not mysql page number
        int max = (int)Math.floor( totalRecord / WOConstant.RECORD_PER_PAGE );
        if(pageNumberCannotBeZero<max){
            return WOConstant.RECORD_PER_PAGE;
        }else if(pageNumberCannotBeZero==max){
            return totalRecord - ( pageNumberCannotBeZero * WOConstant.RECORD_PER_PAGE);
        }else{
            return 0;
        }
    }

    public static String getLimitClause(int pageNumber){
        if(pageNumber<1){
            pageNumber =1;
        }
        int startRow = (pageNumber*10) - 10;
        return startRow+","+WOConstant.RECORD_PER_PAGE;
    }

    public static String getLimitClause(String pageNumber){

        int iPageNumber = 0;
        try {
            iPageNumber = Integer.parseInt(pageNumber);
            return getLimitClause(iPageNumber);
        } catch (Exception e) {
            return getLimitClause(1);
        }
    }

}
