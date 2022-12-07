<%-- 
    Document   : workorder_history
    Created on : Aug 2, 2010, 9:12:22 AM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Order History</title>
        <%
                    com.ckb.wo.client.model.UserBeans ub =
                            (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
                    if (ub != null) {
                        if (!ub.isLogon()) {
                            out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                        }
                    } else {
                        out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                    }
        %>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <style>
            #gridid .x-grid3-hd-row { display:none; }
        </style>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/ext/RowExpander.js"></script>
        <script type="text/javascript">
            Ext.onReady(function(){
                Ext.QuickTips.init();
                var expander = new Ext.ux.grid.RowExpander({
                    tpl : new Ext.Template(
                    '<p><b>From:</b> {employeeid}</p><br>',
                    '<p><b>Date:</b> {datetimeUpdated}</p><br>',
                    '<p><b>Notes</b><hr/> {notes}</p>'
                )
                });

                var store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:['tnotesPk','noteType','subject','datetimeUpdated','employeeid','notes'],
                        id:'tnotesPk',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'notes.do',
                        method:'post'
                    }),
                    baseParams:{
                        wo:${param['wo']},
                        start:0,
                        limit:20
                    },
                    autoLoad:true
                });
                var columns=[
                    expander,
                    {header:'',dataIndex:'noteType', renderer:function(value){return '<b>'+value+'</b>'}},
                    {id:'clmFrom',header:'',dataIndex:'subject'}
                ]
                var vport = new Ext.Viewport({
                    layout:'fit',
                    items:new Ext.grid.GridPanel({
                        border:false,
                        loadMask:true,
                        store:store,
                        columns:columns,
                        hideHeaders:true,
                        autoExpandColumn:'clmFrom',
                        plugins: expander,
                        tbar:[new Ext.PagingToolbar({
                            pageSize: 20,
                            store: store,
                            displayInfo: true,
                            displayMsg: 'Displaying {0} - {1} of {2} Records',
                            emptyMsg: "Empty"
                        }),'-',{
                            xtype:'button',
                            text:'Add Note',
                            handler:function(){
                                location.href='addnote.jsp?wo=${param['wo']}';
                            }
                        }]
                    })
                });
            });
        </script>
    </head>
    <body>
    </body>
</html>
