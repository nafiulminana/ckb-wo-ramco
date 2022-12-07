<%-- 
    Document   : workorder_mass_da
    Created on : Aug 27, 2014, 5:40:51 PM
    Author     : Amaran Sidhiq
--%>

<%@page import="com.shido.jdbc2orm.ResultMap"%>
<%@page import="com.shido.jdbc2orm.JDBCConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mass DA/MPS Upload</title>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
        <script>
            $(document).ready(function() {
                $('#upButton').click(function() {
                    var l = $(parent.document).find("#hloc").val();
                    console.log("loc = " + l);
                    var v = $(parent.document).find("#hven").val();
                    console.log("ven = " + v);
                    if (v === '---' || v === '') {
                        alert('Vendor Not Selected!');
                        return false;
                    }
                    if (l === '---' || l === '') {
                        alert('Location Not Selected!');
                        return false;
                    }
                    var add = 'l=' + l + "&v=" + v;
                    $("#frmMassDa").attr('action', "workorder_mass_da.jsp?" + add);
                    $("#frmMassDa").submit();
                });
            });
        </script>
    </head>
    <body>
        <form id="frmMassDa" method="post" enctype="multipart/form-data">
            upload *.txt file contains DA/MPS#<br/>
            <input type="file" name="das"/>
            <input id="upButton" type="button" name="upButton" value="Upload"/>
        </form>
        <%
            int process = 0, count = 0;
            com.shido.mvc.FormRequests fr = new com.shido.mvc.FormRequests(request);
            if (fr.get("das") != null) {
                com.ckb.wo.client.model.WorkOrder wo = (com.ckb.wo.client.model.WorkOrder) session.getAttribute("wo");
//                if(wo.getTservicetypeFk()==2))
                java.util.List<com.ckb.wo.client.model.Shipment> shipments = (java.util.List<com.ckb.wo.client.model.Shipment>) request.getSession().getAttribute("da");
                if (shipments == null) {
                    shipments = new java.util.ArrayList<com.ckb.wo.client.model.Shipment>();
                }
                boolean valid = true;
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader((java.io.File) fr.get("das")));
                String buff = null;
                out.print("<b>Log:</b><br/>");
                while ((buff = br.readLine()) != null) {
                    count++;
                    out.print(count + ". ");
                    com.ckb.wo.client.model.Shipment shipment = new com.ckb.wo.client.model.Shipment();
                    
                    try {
                        shipment = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getShipmentNotPODByPrimaryKey(new Long(buff));
                        if (shipment == null) {
                            shipment = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getShipmentByPrimaryKey(new Long(buff));
                            if (shipment != null) {
                                shipment.setPod(true);
                                out.print("WARNING: DA/MPS# " + buff + " already POD! ");
                            } else {
                                out.print("DA/MPS# " + buff + " Not Found!<br/>");
                                continue;
                            }
                        }
                    } catch (Exception e) {
                        out.print("DA :" + buff + ", Message : " + e.getMessage());
                    }
                    
                    try {
                        valid = shipment == null ? false : !com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.isDAUsedInWO(shipment.getDa(), new Long(request.getParameter("v")), new Long(request.getParameter("l")));
                    } catch (Exception e) {
                        out.print("Vendor and Location not selected<br/>");
                    }
                    if (!valid) {
                        out.print("DA/MPS# " + buff + " already used by the same vendor and location!<br/>");
                        continue;
                    }
                    for (int i = 0; i < shipments.size(); i++) {
                        if (shipments.get(i).getDa().equals(shipment.getDa())) {
                            valid = false;
                            break;
                        }
                    }
                    JDBCConnector con = new JDBCConnector("fast");
                    ResultMap rs = con.getSingleResult("SELECT * FROM fast.`t_shipment` ts LEFT JOIN fast.`t_mps` tm ON ts.da = tm.da\n"
                                + "WHERE ts.created_date > '2017-05-24 00:00:00' AND ts.da = '"+shipment.getDa()+"' "
                                + "UNION ALL\n"
                                + "SELECT * FROM fast.`t_shipment` ts LEFT JOIN fast.`t_mps` tm ON ts.da = tm.da\n"
                                + "WHERE ts.created_date > '2017-05-24 00:00:00' AND tm.mps = '"+shipment.getDa()+"'");
//                    if(rs == null){
//                        valid = false;
//                    }
                    boolean cutOffAlert = true;

                        if (rs == null) {
                            valid = false;
                            cutOffAlert = true;
                        } else {
                            cutOffAlert = false;
                        }
                    /*JDBCConnector conDa = new JDBCConnector();
                     ResultMap rsDaExist = conDa.getSingleResult("SELECT * FROM db_workorder.`tworkorder_da` WHERE da = '"+shipment.getDa()+"'");
                    if(rsDaExist !=null){
                          valid = false;
                    }*/
                    if (!valid) {
                        if (cutOffAlert) {
                                out.print("WO Cannot be created due to cut-off data since 24 May 2017");
                            }
                        out.print("DA/MPS# " + buff + " Already Exist!<br/>");
                        continue;
                    } else {
                        java.math.BigDecimal totw, totvol;
                        totw = wo.getTotalweight();
                        totvol = wo.getTotalvolume();
                        totw = totw == null ? new java.math.BigDecimal(shipment.getTotWeight()) : totw.add(new java.math.BigDecimal(shipment.getTotWeight()));
                        totvol = totvol == null ? new java.math.BigDecimal(shipment.getTotDim()) : totvol.add(new java.math.BigDecimal(shipment.getTotDim()));
                        wo.setTotalweight(totw);
                        wo.setTotalvolume(totvol);
                        shipments.add(shipment);
                    }
                    out.print(buff + " Inserted!<br/>");
                    process++;
                }
                request.getSession().setAttribute("da", shipments);
            }
            out.print("<br/><b>Success:</b>" + process + " <b>Error:</b>" + (count - process) + " <b>Total:</b>" + count);
        %>
        <div style="display: none" id="result">
            <table  border="1" style="border-collapse: collapse" cellpadding="3">
                <thead>
                    <tr>
                        <th>
                            Action
                        </th>
                        <th>
                            DA#
                        </th>
                        <th>
                            origin
                        </th>
                        <th>
                            destination
                        </th>
                        <th>
                            package
                        </th>
                        <th>
                            weight (Kg)
                        </th>
                        <th>
                            volume (m<sup>3</sup>)
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="da" items="${sessionScope['da']}">
                        <tr style="${da.pod?'background-color: #FFEBE8':''}">
                            <td>
                                <a href="handling.do?remove=${da.da}">remove</a>
                            </td>
                            <td>
                                ${da.da}
                            </td>
                            <td>
                                ${da.origin}
                            </td>
                            <td>
                                ${da.destination}
                            </td>
                            <td>
                                ${da.totPackage}
                            </td>
                            <td>
                                <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${da.totWeight}"/>
                            </td>
                            <td>
                                <fmt:formatNumber groupingUsed="false" maxFractionDigits="3" minFractionDigits="3" value="${da.totDim}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%--<c:if test="${param.upButton!=null}">--%>
        <div id="rtotw" style="display:none">
            <fmt:formatNumber pattern="0.000" value="${sessionScope['wo'].totalweight}"/> Kg
        </div>
        <div id="rtotv" style="display:none">
            <fmt:formatNumber pattern="0.000" value="${sessionScope['wo'].totalvolume}"/> m<sup>3</sup>
        </div>
        <%--</c:if>--%>
        <script>
            $(parent.document).find('#daTab').html($('#result').html());
            $(parent.document).find('#totw').html($('#rtotw').html());
            $(parent.document).find('#totv').html($('#rtotv').html());
        </script>
    </body>
</html>
