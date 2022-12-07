<%-- 
    Document   : fast_user
    Created on : Aug 6, 2010, 3:29:08 PM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                }
                if (!com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.MANAGE_USER)) {
                    out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                }
            } else {
                out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
            }
        %>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/User.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>

        <script type="text/javascript">
            Ext.onReady(function() {
                var listStore = new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields: [
                            'action', 'employeeId', 'firstName', 'middleName', 'lastName',
                            'email', 'areaId', 'divisionId', 'departmentId', 'stationId', 'jobTitleId', 'levelId'
                        ],
                        id: 'employeeId',
                        totalProperty: 'total',
                        root: 'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url: 'user.do',
                        method: 'post'
                    }),
                    baseParams: {
                        t: '${param['sb']!=null?"sboss":"fuser"}',
                        n: '',
                        start: 0,
                        limit: 20
                    },
                    autoLoad: true
                });
                var viewport = new Ext.Viewport({
                    layout: 'fit',
                    items: [
                        new Ext.grid.GridPanel({
                            border: false,
                            id: 'userList',
                            height: 250,
                            frame: false,
                            singleSelect: true,
                            store: listStore,
                            columns: [
                                {header: 'Action', dataIndex: 'action'}, {header: 'Employee Id', dataIndex: 'employeeId'}, {header: 'First Name', dataIndex: 'firstName'}, {header: 'Middle Name', dataIndex: 'middleName'}, {header: 'Last Name', dataIndex: 'lastName'},
                                {header: 'Email', dataIndex: 'email'}, {header: 'Area', dataIndex: 'areaId'}, {header: 'Division', dataIndex: 'divisionId'}, {header: 'Department', dataIndex: 'departmentId'}, {header: 'Station', dataIndex: 'stationId'}, {header: 'Job Title', dataIndex: 'jobTitleId'}, {header: 'Level', dataIndex: 'levelId'}
                            ],
                            tbar: new Ext.PagingToolbar({
                                pageSize: 20,
                                store: listStore,
                                displayInfo: true,
                                displayMsg: 'Displaying {0} - {1} of {2} Records',
                                emptyMsg: "Empty",
                                items: ["-", {
                                        xtype: 'textfield',
                                        emptyText: 'Employee Name',
                                        id: 'empName'
                                    },
                                    {
                                        id: 'find',
                                        text: 'Find',
                                        toolTip: {
                                            title: 'Search Employe',
                                            text: 'Search employee that match the name'
                                        },
                                        handler: function() {
                                            listStore.setBaseParam("n", Ext.getCmp('empName').getValue());
                                            listStore.load();
                                        }

                                    },
            <c:if test="${param['sb']!=null}" >
                                    {
                                        xtype: 'textfield',
                                        emptyText: 'Employee Name',
                                        id: 'empNames'

                                    },
                                    {
                                        id: 'finds',
                                        text: 'Find',
                                        toolTip: {
                                            title: 'Search Employe',
                                            text: 'Search employee that match the name'

                                        },
                                        handler: function() {

                                            listStore.setBaseParam("n", Ext.getCmp('empNames').getValue());

                                            listStore.load();

                                        }

                                    },
//                                    {
//                                        text: 'Clear Boss',
//                                        handler: function() {
//                                            setBoss(null)
//                                        }
//                                    },           
                       </c:if>
                                                                {
                                                                    id: 'clear',
                                                                    text: 'Clear Filter',
                                                                    toolTip: {
                                                                        title: 'Reset Filter',
                                                                        text: 'Reset name filter.'
                                                                    },
                                                                    handler: function() {
                                                                        listStore.setBaseParam("n", "");
                                                                        listStore.load();
                                                                    }

                                                                }
                                                            ]
                                                        })
                                                                ,
                                                        sm: new Ext.grid.RowSelectionModel({
                                                            singleSelect: true
                                                        }),
                                                        listeners: {
                                                            rowclick: function(g, i, e) {
                                                            }
                                                        }
                                                    })
                                                ]
                                            });
            <c:if test="${param['sb']!=null}" >
                                            listStore.setBaseParam("sb", "${param['sb']}");
                                            Ext.getCmp('empName').hide();
                                            Ext.getCmp('find').hide();
                                            Ext.getCmp('clear').hide();
            </c:if>

                });
                function addUser(pk) {
                    var c = confirm("Add this user?");
                    if (c) {
                        User.add(pk, {
                            callback: function(result) {
                                alert(result);
                                parent.load("");
                                parent.SeyLightbox.close();
                            },
                            asycn: false
                        });
                    }
                }
            <c:if test="${param['sb']!=null}" >
                function setBoss(boss) {
                    User.setBoss('${param['sb']}', boss, {
                        callback: function(res) {
                            alert(res);
                            parent.load();
                            parent.SeyLightbox.close();
                        }, async: false
                    });
                }
            </c:if>
        </script>
    </head>
    <body>
    </body>
</html>
