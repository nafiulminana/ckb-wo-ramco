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
        <script type="text/javascript">
            Ext.onReady(function(){
                Ext.QuickTips.init();
                var store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:['actiondate','employeeid','oldwostatus','reason','levelId','onbehalf'],
                        id:'tworkorderhistoryPk',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'history.do',
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
                    {header:'Action Date',dataIndex:'actiondate',renderer:function(value){return new Date(value.time).format("F d, Y H:i:s");}},
                    {header:'Action By',dataIndex:'employeeid'},
                    {header:'On Behalf Of',dataIndex:'onbehalf'},
                    {header:'Action',dataIndex:'oldwostatus',renderer:function(value,md){md.attr="style='text-align:right'";return value.replace("_"," ");}},
                    {header:'Level',dataIndex:'levelId'},
                    {header:'reason',dataIndex:'reason',renderer:function(value,md){
                            md.attr="ext:qtip=\""+value+"\" ext:qtip=\"Reason\"";
                            return value;
                        }}
                ]
                var vport = new Ext.Viewport({
                    layout:'fit',
                    items:new Ext.grid.GridPanel({
                        border:false,
                        loadMask:true,
                        store:store,
                        columns:columns,
                        tbar:new Ext.PagingToolbar({
                            pageSize: 20,
                            store: store,
                            displayInfo: true,
                            displayMsg: 'Displaying {0} - {1} of {2} Records',
                            emptyMsg: "Empty"
                        })
                    })
                });
            });
        </script>
    </head>
    <body>
    </body>
</html>
