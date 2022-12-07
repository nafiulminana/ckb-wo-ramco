<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (!ub.isLogon()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
        }
    } else {
        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
    }

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" xmlns:wdg="http://ns.adobe.com/addt">
    <head>
        <title><%= application.getInitParameter("appTitle")%></title>
        <link rel="stylesheet" href="../main.css" type="text/css" />
        <meta name="author" content="Chandra Adriansyah" />
        <meta name="programmer" content="Amaran Sidhiq" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
        <link rel="stylesheet" href="../images/style.css" type="text/css" />

        <style type="text/css">
            <!--
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
            -->
        </style>
        <jsp:include page="../inc_sxlightbox.jsp"/>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <jsp:include page="../scripts/inc_boxover.jsp"/>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/AppMod.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>
        <script type="text/javascript">
            var store = null;
            Ext.onReady(function () {
                Ext.QuickTips.init();
                Ext.override(Ext.data.Connection, {
                    timeout: 1000000
                });
                store = new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields: ['type', 'adhoc', 'action', 'nomorwo', 'origin', 'destination', 'vendor', 'executiondate', 'charge', 'charges', 'totalweight', 'totalvolume', 'wostatus', 'revisionreason', 'editreason', 'cancelreason', 'editcounter', 'revisioncounter', 'nextApprovalName', 'validationnote', 'level.description'],
                        id: 'tworkorderPk',
                        totalProperty: 'total',
                        root: 'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url: 'list.do',
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
                    {header: 'Action', dataIndex: 'action', width: 200},
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
                    {header: 'Charge', dataIndex: 'charges'},
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
                    {header: 'Last Approval Level', dataIndex: 'level.description'},
                    {header: 'Waiting For Next Approval', dataIndex: 'nextApprovalName'},
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
                    {header: 'Revision#', dataIndex: 'revisioncounter'},
                    {header: 'Edit#', dataIndex: 'editcounter'}
                ];
                Ext.onReady(function () {

                    var panel = new Ext.Panel({
            <%--width:GetWidth()-75,--%>
                        region: 'center',
                        autoScroll: true,
                        items: [
                            {
                                xtype: 'fieldset',
                                title: 'Filter Data',
                                collapsible: true,
                                layout: 'form',
                                collapsed: true, items:
                                        {

                                            xtype: 'form', id: 'frmFilter', border: false,
                                            items: [
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'WO#',
                                                    id: 'search',
                                                    width: 150
                                                }, {
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
                                                }

                                            ]
                                        }, buttons: [{
                                        text: 'Filters',
                                        handler: load
                                    }, {
                                        text: 'Reset Filter',
                                        handler: function (e) {
                                            Ext.getCmp('frmFilter').getForm().reset();
                                        }
                                    }
                                ]},
                            new Ext.TabPanel({
                                activeTab:${ param['t']==null ? "0" : param['t'] },
                                height: 600,
                                items: [
                                    {
                                        title: 'New Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", 'new');
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
                                    }, {
                                        title: 'Need Action Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", 'action');
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
                                    }, {
                                        title: 'OnGoing Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", 'ongoing');
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
                                    }, {
                                        title: 'Approved Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", '<%= com.ckb.wo.client.model.WorkOrder.APPROVED_STATUS%>');
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
                                    }, {
                                        title: 'Printed Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", '<%= com.ckb.wo.client.model.WorkOrder.PRINTED_STATUS%>');
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
                                    }, {
                                        title: 'Canceled Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", '<%= com.ckb.wo.client.model.WorkOrder.CANCELLED_STATUS%>');
                                                store.load();
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
                                    }, {
                                        title: 'Created Work Order',
                                        layout: 'fit',
                                        listeners: {
                                            activate: function (tab) {
                                                store.setBaseParam("t", '');
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
                        items: [
                            {
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
                                items: [
                                    {
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
                store.removeAll();
                store.setBaseParam("search", Ext.getCmp('search').getRawValue());
                store.setBaseParam("dateFrom", Ext.getCmp('dtFrom').getRawValue());
                store.setBaseParam("dateTo", Ext.getCmp('dtTo').getRawValue());
                store.load();
            }
            function viewWo(id, type) {
                SeyLightbox.display('viewwo.jsp?wo=' + id + '&t=' + type + '&TB_iframe=1&width=700&height=' + (GetHeight() - 90));
            }
            function viewWoPrint(id, type) {
                SeyLightbox.display('viewwoprint.jsp?wo=' + id + '&t=' + type + '&TB_iframe=1&width=700&height=' + (GetHeight() - 90));
            }
            function viewNotes(id) {
                SeyLightbox.display('workorder_notes.jsp?wo=' + id + '&TB_iframe=1&width=700&height=' + (GetHeight() - 90));
            }
            function sendReminder(twok) {
                var c = confirm("Are you sure to send a reminder ?");
                if (c) {
                    AppMod.sendReminder(twok, {
                        callback: function (result) {
                            alert(result);
                        },
                        async: false
                    });
                }
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
                                                                <%--<div id="filter">
                                                                    <form>

                                                                        <table border="0" style="padding: 3px 3px 3px 3px">
                                                                            <tr>
                                                                                <td>WO#</td>
                                                                                <td><input type="text" name="search" id="search" value="" /></td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>Date From </td>
                                                                                <td>
                                                                                    <table border="0" cellpadding="3">
                                                                                        <tr>
                                                                                            <td><input type="text" name="from" value="" id="from" readonly /><input type="hidden" name="hfFrom" value="" id="hfFrom"  /></td>
                                                                                            <td>To</td>
                                                                                            <td><input type="text" name="to" value="" id="to" readonly/><input type="hidden" name="hfTo" id="hfTo" value="" /></td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>

                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    Origin
                                                                                </td>
                                                                                <td>
                                                                                    <select name="origin">
                                                                                        <option value="---">Select Origin</option>
                                                                                        <c:forEach var="origin" items="${requestScope['location']}">
                                                                                            <option value="${origin.tlocationPk}" ${origin.tlocationPk==sessionScope['wo'].origintlocationFk?"selected":""}>${origin.locationname}</option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    Destination
                                                                                </td>
                                                                                <td>
                                                                                    <select name="destination">
                                                                                        <option value="---">Select Destination</option>
                                                                                        <c:forEach var="destination" items="${requestScope['location']}">
                                                                                            <option value="${destination.tlocationPk}" ${destination.tlocationPk==sessionScope['wo'].destinationtlocationFk?"selected":""}>${destination.locationname}</option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <input type="button" value="Filter" name="bFilter" id="bFilter" /><input type="reset" value="Reset Filter" id="reset" />
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </form>
                                                                </div>--%>
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
