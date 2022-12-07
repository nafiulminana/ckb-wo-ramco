package com.ckb.wo.report;

import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.client.model.WorkOrderExample;
import com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class PreviewWO extends HttpServlet {

    final static Logger log = Logger.getLogger(PreviewWO.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.gc();
        try {
            String wono = request.getParameter("wono");
            WorkOrderExample workOrderExample = new WorkOrderExample();
            WorkOrderExample.Criteria criteria = workOrderExample.createCriteria();
            criteria.andNomorwoEqualTo(wono);
            List<WorkOrder> wos = WorkOrderLocalServiceUtil.getWorkOrderByExample(workOrderExample);
            for (WorkOrder wo : wos) {
                ReportHelper.print(response.getOutputStream(), wo.getTworkorderPk(), wo.getTservicetypeFk() == 2);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}