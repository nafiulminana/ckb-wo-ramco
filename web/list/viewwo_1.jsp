<%-- 
    Document   : viewWo.jsp
    Created on : Jul 8, 2010, 8:55:10 PM
    Author     : Amaran Sidhiq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            try {
                Long id = new Long(request.getParameter("wo"));
                com.ckb.wo.client.model.WorkOrder wo = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(id);
                request.setAttribute("wo", wo);
                java.util.Iterator<com.ckb.wo.client.model.WorkOrderManifest> it = wo.getWorkOrderManifest().iterator();
                java.util.List<com.ckb.wo.client.model.ManifestWeightVolumeDetail> manifests = new java.util.ArrayList<com.ckb.wo.client.model.ManifestWeightVolumeDetail>();
                while (it.hasNext()) {
                    manifests.add(com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(it.next().getManifestNo()));
                }
                request.setAttribute("manifests", manifests);
            } catch (Exception e) {
                request.setAttribute("errorMessage", "No Work Order document to display!");
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Order</title>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/WorkOrder.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>
        <% Boolean app = (request.getParameter("a").charAt(request.getParameter("a").length() - 1) == '1'); %>
        <c:if test="app">

            <script type="text/javascript">
                function approve(){
                    var reason=prompt("Enter approval note", "");
                    if(reason!==null)
                        WorkOrder.approve(${param['wo']},reason,{
                            callback:function(result){
                                if(result){
                                    alert('Document Approved!');
                                    parent.SeyLightbox.close();
                                }else{
                                    alert('Approval Failed!');
                                }
                        }
                    });
                }
                function edit(){
                    var reason=prompt("Enter approval note", "");
                    if(reason)
                        WorkOrder.edit(${param['wo']},reason,{
                            callback:function(result){
                                if(result){
                                    alert('Document Sent for edit!');
                                    parent.SeyLightbox.close();
                                }else{
                                    alert('Edit failed!');
                                }
                        }
                    });
                }
                function cancel(){
                    var reason=prompt("Enter cancelation note", "");
                    if(reason)
                        WorkOrder.edit(${param['wo']},reason,{
                            callback:function(result){
                                if(result){
                                    alert('Document Canceled!');
                                    parent.SeyLightbox.close();
                                }else{
                                    alert('Cancelation Failed!');
                                }
                        }
                    });
                }
            </script>
        </c:if>
    </head>
    <body style="font-family:Arial,Times New Roman">
        <c:if test="app">
            <div id="toolbar">
                <input type="button" value="Approve" onclick="approve()" /><input type="button" value="Edit" onclick="edit()" /><input type="button" value="Cancel/Reject" onclick="cancel()"/>
            </div>
        </c:if>
        <table width="100%" border="0" align="center" cellpadding="3">
            <tr>
                <td colspan="2">
                    <c:if test="${requestScope['errorMessage']!=null}">
                        <div style="border:dotted red thin"> ${requestScope['errorMessage']} </div>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="2"><div align="center"><h1 style="border: solid black thin;padding: 10px 10px 10px 10px">Work Order # ${requestScope['wo'].nomorwo}</h1> </div></td>
            </tr>
            <tr>
                <td colspan="2"><table border="0" cellpadding="3">
                        <tr>
                            <td nowrap><strong>Origin</strong></td>
                            <td>${requestScope['wo'].origin.locationname}</td>
                        </tr>
                        <tr>
                            <td><strong>Destination</strong></td>
                            <td>${requestScope['wo'].destination.locationname}</td>
                        </tr>
                        <tr>
                            <td><strong>Execution Date</strong></td>
                            <td><fmt:formatDate pattern="MMMM, dd, yyyy" value="${requestScope['wo'].executiondate}"/></td>
                        </tr>
                        <tr>
                            <td valign="top"><strong>Vendor</strong></td>
                            <td><table border="0" cellpadding="3">
                                    <tr>
                                        <td>Name</td>
                                        <td>${requestScope['wo'].vendor.vendorname}</td>
                                    </tr>
                                    <tr>
                                        <td>Address</td>
                                        <td>${requestScope['wo'].vendor.address1}</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>${requestScope['wo'].vendor.address2}</td>
                                    </tr>
                                    <tr>
                                        <td>Contact Phone</td>
                                        <td>${requestScope['wo'].vendor.contactname}(${requestScope['wo'].vendor.contactphone})</td>
                                    </tr>
                                    <tr>
                                        <td>Fax</td>
                                        <td>${requestScope['wo'].vendor.contactfax}</td>
                                    </tr>
                                    <tr>
                                        <td>Website/Email</td>
                                        <td>${requestScope['wo'].vendor.website}/${requestScope['wo'].vendor.email}</td>
                                    </tr>
                                </table></td>
                        </tr>
                    </table></td>
            </tr>
            <tr>
                <td colspan="2">
                    <table  border="1" style="border-collapse: collapse;padding: 5px 5px 5px 5px">
                        <thead>
                            <tr>
                                <th> manifest# </th>
                                <th> origin </th>
                                <th> destination </th>
                                <th> actual shipment </th>
                                <th> package </th>
                                <th> weight </th>
                                <th> volume </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="manifest" items="${requestScope['manifests']}">
                                <tr>
                                    <td> ${manifest.manifest_no} </td>
                                    <td> ${manifest.ori} </td>
                                    <td> ${manifest.dest} </td>
                                    <td><fmt:formatDate value="${manifest.actual_ship_date==null ? manifest.ship_date : manifest.actual_ship_date}"/></td>
                                    <td> ${manifest.jmlh_da} </td>
                                    <td> <fmt:formatNumber pattern="0.000" value="${manifest.weight}"/> </td>
                                    <td> <fmt:formatNumber pattern="0.000" value="${manifest.volume}"/> </td>
                                </tr>
                                <tr>
                                    <td colspan="5">&nbsp;</td>
                                    <td><fmt:formatNumber pattern="0.000" value="${requestScope['wo'].totalweight}"/></td>
                                    <td><fmt:formatNumber pattern="0.000" value="${requestScope['wo'].totalvolume}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table></td>
            </tr>
            <tr>
                <td><table border="0" cellpadding="3">
                        <tr>
                            <td><strong>Container Mode</strong></td>
                            <td>
                                <c:forEach var="container" items="${requestScope['wo'].workOrderServiceModeDetail}">
                                    ${container.servicemodeDetail.smdname} x${container.quantity}<br/>
                                </c:forEach></td>
                        </tr>
                        <tr>
                            <td><strong>Delivery Terms</strong></td>
                            <td>
                                //tidak ada di bean
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Goods Description</strong></td>
                            <td>${requestScope['wo'].gooddescription}</td>
                        </tr>
                        <tr>
                            <td><strong>Delivery Note</strong></td>
                            <td>${requestScope['wo'].deliverynote}</td>
                        </tr>
                        <tr>
                            <td><strong>Last Approved By</strong></td>
                            <td>${requestScope['wo'].lasttlevel}</td>
                        </tr>
                        <tr>
                            <td><strong>Document Status</strong></td>
                            <td>${requestScope['wo'].wostatus}</td>
                        </tr>
                    </table></td>
                <td valign="top"><table border="0">
                        <tr>
                            <td valign="top" align="right"><strong>Charge</strong></td>
                            <td><fmt:formatNumber currencySymbol="${requestScope['wo'].currency.currencycode}"value="${requestScope['wo'].charge}"/></td>
                        </tr>
                        <c:if test="${requestScope['wo'].ppn!=null}">
                            <tr>
                                <td valign="top" align="right"><strong>VAT</strong></td>
                                <td><fmt:formatNumber pattern="0.000" value="${requestScope['wo'].ppn}"/>%</td>
                            </tr>
                        </c:if>
                        <c:if test="${requestScope['wo'].ppn!=null}">
                            <tr>
                                <td valign="top" align="right"><strong>PPH</strong></td>
                                <td><fmt:formatNumber pattern="0.000" value="${requestScope['wo'].pph}"/>%</td>
                            </tr>
                        </c:if>
                        <tr>
                            <td valign="top" align="right"><strong>Total</strong></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table></td>
            </tr>
        </table>
    </body>
</html>
