<%-- 
    Document   : viewWo.jsp
    Created on : Jul 8, 2010, 8:55:10 PM
    Author     : Amaran Sidhiq
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.shido.jdbc2orm.ResultMap"%>
<%@page import="com.shido.jdbc2orm.JDBCConnector"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (!ub.isLogon()) {
            out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
        }
    } else {
        out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
    }

    try {
        Long id = new Long(request.getParameter("wo"));
        com.ckb.wo.client.model.WorkOrder wo = com.ckb.wo.server.service.util.WorkOrderLocalServiceUtil.getWorkOrderByPrimaryKeyWithJoin(id);
        request.setAttribute("wo", wo);
        java.util.Iterator<com.ckb.wo.client.model.WorkOrderManifest> it = wo.getWorkOrderManifest().iterator();
        java.util.List<com.ckb.wo.client.model.ManifestWeightVolumeDetail> manifests = new java.util.ArrayList<com.ckb.wo.client.model.ManifestWeightVolumeDetail>();
        while (it.hasNext()) {
            Long manNo = it.next().getManifestNo();
            com.ckb.wo.client.model.ManifestWeightVolumeDetail manifest = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getManifestWeightVolumeDetail(manNo);
            if (manifest == null) {
                manifest = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getPickupDeliveryWeightVolumeDetail(manNo);
                if (manifest == null) {
                    throw new Exception("Manifest number is not found");
                }
            }
            manifests.add(manifest);
        }
        request.setAttribute("manifests", manifests);

        JDBCConnector con = new JDBCConnector();
        ResultMap rsBaseLineCharge = con.getSingleResult("SELECT SUM(quantity * smdcharge) as charge FROM tworkorder_smodedetail WHERE tworkorder_fk = " + id + " AND deleted = 0 GROUP BY tworkorder_fk, tservicemodedetail_fk, smdcharge, smdremarks");
        List<ResultMap> lsCompareCharge = con.getQuery("SELECT DISTINCT quantity, smdcharge FROM `tworkorder_smodedetail` WHERE tworkorder_fk = " + id + " AND deleted = 0 ");
        float baseLineCharge = 0f;
        float compareCharge = 0f;
        //   boolean statusErrorCharge = false;

        if (rsBaseLineCharge != null) {
            baseLineCharge = rsBaseLineCharge.getFloat("charge") * 1.0f;
            request.setAttribute("baseLineCharge", baseLineCharge);
        }
        if (lsCompareCharge != null) {
            Iterator<ResultMap> itr = lsCompareCharge.iterator();
            while (itr.hasNext()) {
                ResultMap rsCompareCharge = itr.next();
                compareCharge += (rsCompareCharge.getFloat("quantity") * rsCompareCharge.getFloat("smdcharge") * 1.0);
            }
        }
        // DecimalFormat formatter  = new DecimalFormat("#,###.00");

        request.setAttribute("compareCharge", String.format("%", compareCharge));
        if (baseLineCharge > compareCharge) {
            // statusErrorCharge = true;
            request.setAttribute("statusErrorCharge", true);
        } else {
            request.setAttribute("statusErrorCharge", false);
        }
    } catch (Exception e) {
        request.setAttribute("errorMessage", "No Work Order document to display!");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Order</title>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/AppMod.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/WorkOrder.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>
        <%
            Boolean app = false;
            try {
                app = new Boolean(request.getParameter("a"));
            } catch (Exception e) {
                app = false;
            }
            request.setAttribute("app", app);
        %>
        <c:if test="${requestScope['app']}">
            <script type="text/javascript">
                function approve() {
                    var reason = confirm('Approve this document?');
                    if (reason)
                        WorkOrder.approve(${param['wo']}, reason, {
                            callback: function (result) {
                                if (result) {
                                    alert('Document Approved!');
                                } else {
                                    alert('Approval Failed!');
                                }
                                parent.SeyLightbox.close();
                            },
                            async: false
                        });
                    parent.load();
                }
                function edit() {
                    var reason = prompt("Enter edit reason", "");
                    if (reason)
                        WorkOrder.edit(${param['wo']}, reason, {
                            callback: function (result) {
                                if (result) {
                                    alert('Document Sent for edit!');
                                    parent.SeyLightbox.close();
                                } else {
                                    alert('Edit failed!');
                                }
                            },
                            async: false
                        });
                    parent.load();
                }

                function cancel() {
                    var currencies = "";
                    AppMod.getCurrency({
                        callback: function (result) {
                            currencies = result;
                        }, async: false
                    });
                    cancelWindow = new Ext.Window({
                        title: 'Cancelling Work Order',
                        closable: false,
                        modal: true,
                        resizable: true,
                        width: 433,
                        bodyStyle: 'padding: 5px; background-color: white;',
                        items: {
                            xtype: 'form',
                            border: false,
                            items: [
                                {
                                    width: 302,
                                    xtype: 'textarea',
                                    fieldLabel: 'Reason',
                                    id: 'cancelReason',
                                    value: '',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'compositefield',
                                    fieldLabel: 'Cancellation Fee',
                                    items: [
                                        {
                                            width: 150,
                                            xtype: 'combo',
                                            mode: 'local',
                                            triggerAction: 'all',
                                            forceSelection: true,
                                            editable: false,
                                            fieldLabel: 'Currency',
                                            id: 'currency',
                                            displayField: 'currencyname',
                                            valueField: 'tcurrencyPk',
                                            emptyText: 'Select a currency...',
                                            store: new Ext.data.JsonStore({
                                                fields: ['tcurrencyPk', 'currencyname'],
                                                data: currencies
                                            }),
                                            allowBlank: false
                                        },
                                        {
                                            xtype: 'numberfield',
                                            id: 'cancellationFee',
                                            fieldLabel: 'Cancellation Fee',
                                            allowBlank: false
                                        }
                                    ]
                                }
                            ]
                        },
                        buttons: [
                            {
                                text: 'Cancel this WO',
                                handler: function () {
                                    var cancelReason = Ext.getCmp('cancelReason').getValue().trim();
                                    var currency = Ext.getCmp('currency').getValue();
                                    var cancellationFee = Ext.getCmp('cancellationFee').getValue();
                                    if (cancelReason === null || cancelReason === "") {
                                        Ext.MessageBox.show({
                                            title: "Error",
                                            msg: "Please fill in The Reason Field",
                                            buttons: Ext.MessageBox.OK,
                                            icon: Ext.MessageBox.ERROR
                                        });
                                        return false;
                                    }
                                    if (currency === null || currency === "") {
                                        Ext.MessageBox.show({
                                            title: "Error",
                                            msg: "Please fill in The Currency Field",
                                            buttons: Ext.MessageBox.OK,
                                            icon: Ext.MessageBox.ERROR
                                        });
                                        return false;
                                    }
                                    if (cancellationFee === null || cancellationFee === "") {
                                        Ext.MessageBox.show({
                                            title: "Error",
                                            msg: "Please fill in The Cancellation Fee Field",
                                            buttons: Ext.MessageBox.OK,
                                            icon: Ext.MessageBox.ERROR
                                        });
                                        return false;
                                    }
                                    var myMask = new Ext.LoadMask(Ext.getBody(), {msg: "Please wait..."});
                                    myMask.show();
                                    WorkOrder.cancel(${param['wo']}, cancelReason, cancellationFee, currency, {
                                        callback: function (result) {
                                            if (result) {
                                                parent.SeyLightbox.close();
                                                myMask.hide();
                                                cancelWindow.close();
                                                cancelWindow = null;
                                                parent.load();
                                            }
                                        }
                                    });
                                }
                            },
                            {
                                text: 'Close',
                                handler: function (e) {
                                    cancelWindow.close();
                                    cancelWindow = null;
                                }
                            }
                        ]
                    });
                    cancelWindow.show();
                }
            </script>
        </c:if>
    </head>
    <body style="font-family:Arial,Times New Roman">
        <c:if test="${requestScope['statusErrorCharge']}">
            <div style="color: #fff; background-color: #ff0000;">
                ERROR CHARGE - Real Charge Calculation is ${requestScope['compareCharge']}
            </div>


        </c:if>

        <c:if test="${requestScope['app']}">
            <div id="toolbar">

                <input type="button" value="Approve" onclick="approve()" /><input type="button" value="Edit" onclick="edit()" /><input type="button" value="Cancel/Reject" onclick="cancel()"/>

            </div>

        </c:if>

        <iframe id="frame" style="width: 100%;height: 100%" src="print.do?h=true&wo=${param['wo']}&t=${param['t']}" frameborder="0"></iframe>
        <!--<iframe id="frame" style="width: 100%;height: 500px;" src="http://10.10.3.88/CKB.CCOS/WO/reportwo/${param['wo']}#toolbar=0&navpanes=0" frameborder="0"></iframe>-->
        <script type="text/javascript">
            function resizeIframe() {
                var height = document.documentElement.clientHeight;
                height -= document.getElementById('frame').offsetTop;
                height -= 20;
                document.getElementById('frame').style.height = height + "px";
            }
            ;
            document.getElementById('frame').onload = resizeIframe;
            window.onresize = resizeIframe;
        </script>

    </body>
</html>
