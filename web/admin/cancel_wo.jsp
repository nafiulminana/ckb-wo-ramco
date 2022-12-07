<%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }
                if (!com.ckb.wo.server.service.util.PermissionLocalServiceUtil.getUserCanCanceledWo(ub.getEmployeeId())) {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }

            } else {
                response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            }
%>
<%-- 
    Document   : reject_wo
    Created on : Feb 21, 2022, 9:30:34 AM
    Author     : nafiu
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" xmlns:wdg="http://ns.adobe.com/addt">
    <head>
        <title><%= application.getInitParameter("appTitle") %></title>
        <link rel="stylesheet" href="<%= application.getContextPath()%>/main.css" type="text/css" />
        <meta name="author" content="Chandra Adriansyah" />
        <meta name="programmer" content="Nafiul Minan" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
        <link rel="stylesheet" href="<%= application.getContextPath()%>/images/style.css" type="text/css" />
        <style type="text/css"> 
            body {
                margin-left: 10px;
                margin-right: 10px;
            }
            .style5 {
                font-size: 16px;
                font-weight: bold;
            }
            .wawa {
                color: #FFF;
            }
            .qa {
                color: #000;
                font-weight: bold;
            }
            .style14 {
                font-size: 10px;
                color: #0000FF;
            }
            .style15 {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 10px;
            }
            .style16 {color: #FF0000}
            table,td
            {
                border-collapse      : collapse;
                text-align:left;
                font                 : small/1.5 "Tahoma", "Bitstream Vera Sans", Verdana, Helvetica, sans-serif;
            } 
        </style>
        <jsp:include page="../inc_sxlightbox.jsp"/>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/Admin.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>  
        <script type="text/javascript">
            var store = null;
            Ext.onReady(function () {
                Ext.QuickTips.init();
                Ext.override(Ext.data.Connection, {timeout: 1000000});
                store = new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields: ['adhoc', 'action', 'nomorwo', 'origin', 'destination', 'vendor', 'executiondate', 'charge', 'charges', 'type', 'totalweight', 'totalvolume', 'wostatus', 'revisionreason', 'editreason', 'cancelreason', 'editcounter', 'validationnote', 'revisioncounter'],
                        id: 'tworkorderPk',
                        totalProperty: 'total',
                        root: 'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url: 'admin.do',
                        method: 'post'
                    }),
                    baseParams: {
                        start: 0,
                        limit: 20
                    }
                });
                function format(value) {
                    return new Date(value.time);
                }
                var columns = [
                    {header: 'Action', dataIndex: 'action', width: 240},
                    {header: 'wo#', dataIndex: 'nomorwo',
                        width: 170,
                        renderer: function (value, metaData, record, rowIndex, colIndex, store) {
                            return '<b>' + value + '</b>';
                        }},
                    {header: 'Origin', dataIndex: 'origin', renderer: function (value, metaData) {
                            metaData.attr = 'style="color:green"';
                            return value.locationname;
                        }
                    },
                    {header: 'Destination', dataIndex: 'destination', renderer: function (value, metaData) {
                            metaData.attr = 'style="color:darkblue"';
                            return value.locationname;
                        }},
                    {header: 'Type', dataIndex: 'type'},
                    {header: 'Ad-Hoc', dataIndex: 'adhoc', renderer: function (v, m) {
                            return v ? "Yes" : "No";
                        }},
                    {header: 'Charge', dataIndex: 'charge', renderer: Ext.util.Format.numberRenderer('0,000.00'), hidden: true},
                    {header: 'Charges', dataIndex: 'charges'},
                    {header: 'Vendor Name', width: 250, dataIndex: 'vendor', renderer: function (value) {
                            return value.vendorname;
                        }},
                    {header: 'Execution Date', dataIndex: 'executiondate', renderer: function (value) {
                            return new Date(value.time).format("F d, Y");
                        }},
                    {header: 'Total Weight', dataIndex: 'totalweight', renderer: function (value, md) {
                            md.attr = "style='text-align:right'";
                            return value;
                        }},
                    {header: 'Total Dimension', dataIndex: 'totalvolume', renderer: function (value, md) {
                            md.attr = "style='text-align:right'";
                            return value;
                        }},
                    {header: 'WO Status', dataIndex: 'wostatus', renderer: function (value, md) {
                            md.attr = "style='text-align:right'";
                            return value.replace("_", " ");
                        }},
                    {header: 'Revision Reason', dataIndex: 'revisionreason',
                        renderer: function (value, md) {
                            md.attr = "ext:qtip='" + value + "' ext:qtitle='Revisio Reason'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length > 20 ? 20 : fv.length) + "...";
                        }},
                    {header: 'Edit Reason', dataIndex: 'editreason',
                        renderer: function (value, md) {
                            md.attr = "ext:qtip='" + value + "' ext:qtitle='Edit Reason'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length > 20 ? 20 : fv.length) + "...";
                        }
                    },
                    {header: 'Validation Note', dataIndex: 'validationnote',
                        renderer: function (value, md) {
                            md.attr = "ext:qtip='" + value + "' ext:qtitle='validationnote'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length > 20 ? 20 : fv.length) + "...";
                        }
                    },
                    {header: 'Cancel Reason', dataIndex: 'cancelreason',
                        renderer: function (value, md) {
                            md.attr = "ext:qtip='" + value + "' ext:qtitle='Cancel Reason'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length > 20 ? 20 : fv.length) + "...";
                        }},
                    {header: 'Revision#', dataIndex: 'editcounter', hidden: true},
                    {header: 'Edit#', dataIndex: 'revisioncoutner', hidden: true}
                ];
                
                Ext.onReady(function () {
                    var panel = new Ext.Panel({
                        region: 'center',
                        autoScroll: true, 
                        items: [
                            {
                                xtype: 'fieldset',
                                title: 'Filter Data',
                                collapsible: true,
                                layout: 'form',
                                collapsed: true,
                                items: {
                                    xtype: 'form',
                                    id: 'frmFilter',
                                    border: false,
                                    items: [{
                                            xtype: 'datefield',
                                            fieldLabel: 'From',
                                            format: 'Y-m-d',
                                            id: 'dtFrom',
                                            width: 150
                                        }, {
                                            xtype: 'datefield',
                                            fieldLabel: 'To',
                                            format: 'Y-m-d',
                                            id: 'dtTo',
                                            width: 150
                                        }, {
                                            xtype: 'textfield',
                                            fieldLabel: 'WO#',
                                            id: 'search',
                                            width: 150,
                                            enableKeyEvents: true,
                                            listeners: {
                                                keyup: function (field) {
                                                    var wono = field.getValue();
                                                    var re = /^(WO|wo|Wo|wO)-\d{14}-\d{3}/;
                                                    if (re.test(field.getValue())) {
                                                        load();
                                                        if (Ext.getCmp('woPreview').getValue()) {
                                                            SeyLightbox.display("../list/previewWO.do?wono=" + wono + "&t=false&TB_iframe=1&width=700&height=" + (GetHeight() - 90));
                                                        }
                                                        field.setValue("");
                                                    }
                                                },
                                                change: function (field) {
                                                    var wono = field.getValue();
                                                    var re = /^(WO|wo|Wo|wO)-\d{14}-\d{3}/;
                                                    if (re.test(field.getValue())) {
                                                        load();
                                                        if (Ext.getCmp('woPreview').getValue()) {
                                                            SeyLightbox.display("../list/previewWO.do?wono=" + wono + "&t=false&TB_iframe=1&width=700&height=" + (GetHeight() - 90));
                                                        }
                                                        field.setValue("");
                                                    }
                                                }
                                            }
                                        }, {
                                            xtype: 'checkbox',
                                            boxLabel: 'WO Preview',
                                            id: 'woPreview'
                                        }
                                    ]
                                },
                                buttons: [{
                                        text: 'Filters',
                                        handler: load
                                    }, {
                                        text: 'Reset Filter',
                                        handler: function (e) {
                                            Ext.getCmp('frmFilter').getForm().reset();
                                        }
                                    }
                                ]
                            },
                            new Ext.TabPanel({
                                activeTab:${ param['t']==null ? "0" : param['t'] },
                                height: 600,
                                items: [{
                                        title: 'Work Order Action Cancel List',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) { 
                                                load();
                                            }
                                        },
                                        items: [
                                            new Ext.grid.GridPanel({
                                                loadMask: true,
                                                store: store,
                                                columns: columns,
                                                tbar: new Ext.PagingToolbar({
                                                    pageSize: 20,
                                                    store: store,
                                                    displayInfo: true,
                                                    displayMsg: 'Displaying {0} - {1} of {2} Records',
                                                    emptyMsg: "Empty"
                                                })
                                            })
                                        ]
                                    }
                                ]
                            })
                        ]
                    });
                    var menupanel = new Ext.Panel({
                        layout: 'border',
                        width: GetWidth() - 90,
                        height: 600,
                        renderTo: 'tabspane',
                        items: [{
                                split: true,
                                title: '<a href="<%=getServletContext().getContextPath()%>/main.jsp">Main Menu</a>',
                                layout: 'accordion',
                                collapsible: true,
                                region: 'west',
                                width: '15%',
                                defaults: {
                                    bodyStyle: 'padding: 3px 3px 3px 3px',
                                    border: true
                                },
                                items: [{
                                        title: 'Work Order',
                                        autoLoad: '<%= application.getContextPath()%>/menu?wo=true'
                                    },
                                    {
                                        title: 'Vendor Management',
                                        autoLoad: '<%= application.getContextPath()%>/menu?proc=true'
                                    },
                                    {
                                        title: 'Work Order Approval',
                                        autoLoad: '<%= application.getContextPath()%>/menu?app=true'
                                    },
                                    {
                                        title: 'User Administration', autoLoad: '<%= application.getContextPath()%>/menu?adm=true'
                                    }
                                ]
                            }, panel
                        ]
                    });
                });
            });
            
            function load() {
                store.setBaseParam("search", Ext.getCmp('search').getRawValue());
                store.setBaseParam("dateFrom", Ext.getCmp('dtFrom').getRawValue());
                store.setBaseParam("dateTo", Ext.getCmp('dtTo').getRawValue());
                store.load();
            }
            
            function viewWo(id, type) {
                SeyLightbox.display('../list/viewwo.jsp?wo=' + id + '&t=' + type + '&TB_iframe=1&width=700&height=' + (GetHeight() - 90));
            }
            
            function viewNotes(id) {
                SeyLightbox.display('../list/workorder_notes.jsp?wo=' + id + '&TB_iframe=1&width=700&height=' + (GetHeight() - 90));
            }
             
            function cancel(twok) { 
                
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
                            } 
                        ]
                    },
                    buttons: [
                        {
                            text: 'Cancel this WO',
                            handler: function () {
                                var cancelReason = Ext.getCmp('cancelReason').getValue().trim(); 
                                if (cancelReason === null || cancelReason === "") {
                                    Ext.MessageBox.show({
                                        title: "Error",
                                        msg: "Please fill in The Reason Field",
                                        buttons: Ext.MessageBox.OK,
                                        icon: Ext.MessageBox.ERROR
                                    });
                                    return false;
                                } 
                                var myMask = new Ext.LoadMask(Ext.getBody(), {msg: "Please wait..."});
                                myMask.show();
                                Admin.cancel(twok, cancelReason, {
                                    callback: function (result) {
                                        if (result) {
                                            myMask.hide();
                                            cancelWindow.close();
                                            cancelWindow = null; 
                                            load();
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
    </head>
    <body>
        <div class="content">
            <!-- header -->
            <jsp:include page="../header.jsp"/>
            <!-- end header -->
            <div>
                <div class="uedge">
                    <div class="redge">
                        <div class="bedge">
                            <div class="ledge">
                                <div class="ulcorner">
                                    <div class="urcorner">
                                        <div class="blcorner">
                                            <div class="brcorner">
                                                <div class="innercontent"> 
                                                    <table width="100%" border="0" bgcolor="#FFFFFF">
                                                        <tr>
                                                            <td >
                                                                <div id="tabspane"></div> 
                                                            </td>
                                                        </tr>

                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- footer -->
        <jsp:include page="../footer.jsp"/>
        <!-- end footer -->
    </body>
</html>
