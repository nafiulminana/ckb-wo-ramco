/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.Vendor;
import com.ckb.wo.client.model.VendorExample;
import com.ckb.wo.server.service.util.VendorLocalServiceUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Amaran Sidhiq
 */
public class VendorListener extends HttpServlet {

    public String setActive(Long vpk){
        Vendor vendor = VendorLocalServiceUtil.getVendorByPrimaryKey(vpk);
        if(!vendor.getIsactive()){
            vendor.setIsactive(true);
            vendor.setInactivereason("");
            vendor.setInactivefrom(null);
            vendor.setInactiveto(null);
            VendorLocalServiceUtil.updateVendor(vendor);
        }else
            return vendor.getVendorname() + " already activated!";
        return vendor.getVendorname() + " is activated!";
    }
    public String setDeactive(Long vpk,String reason, Date from, Date to){
        Vendor vendor = VendorLocalServiceUtil.getVendorByPrimaryKey(vpk);
        if(vendor.getIsactive()){
            if(from==null || to==null)
               return "Inactive date cannot be empty";
            vendor.setIsactive(false);
            vendor.setInactivereason(reason);
            vendor.setInactivefrom(from);
            vendor.setInactiveto(to);
            VendorLocalServiceUtil.updateVendor(vendor);
        }else
            return vendor.getVendorname() + " already deactivated!";
        return vendor.getVendorname() + " is deactivated!";
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            VendorExample example = new VendorExample();
            String search = null;
            VendorExample.Criteria criteria = example.createCriteria();
            if (req.getParameter("search") != null) {
                search = req.getParameter("search");
                if (search != null) {
                    criteria.andVendornameLike("%" + search.replaceAll("\'", "\'\'") + "%");
                }
            }
            int totalVendor = VendorLocalServiceUtil.countVendorByExample(example);
            int start = 0;
            int limit = 10;
            try {
                start = new Integer(req.getParameter("start"));
                limit = new Integer(req.getParameter("limit"));
            } catch (Exception e) {
            }

            example.setLimitClause(start + "," + limit);
            List<Vendor> vendor = VendorLocalServiceUtil.getVendorByExample(example);
            JSONArray data = new JSONArray();
            Iterator<Vendor> it = vendor.iterator();
            while(it.hasNext()){
                Vendor o = it.next();
                JSONObject obj = JSONObject.fromObject(o);
                obj.put("action", "<a href='#' onclick='edit("+o.getTvendorPk()+")'>Edit</a>");
                obj.put("action", obj.get("action")+" | <a href='#' onclick='set"+(o.getIsactive()?"Dea":"A")+"ctive("+o.getTvendorPk()+")'>"+(o.getIsactive()?"Dea":"A")+"ctive</a>");
                data.add(obj);
            }

            Map map = new HashMap();
            map.put("total", totalVendor);
            map.put("data", data);

            JSONObject obj = JSONObject.fromObject(map);
            obj.write(out);
        } finally {
            out.close();
        }
    }

}
