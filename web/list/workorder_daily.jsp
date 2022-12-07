<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }
                if (!com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.VIEW_REPORT)) {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }

            } else {
                response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            }
            java.util.List<com.ckb.wo.client.model.Location> location = com.ckb.wo.server.service.util.LocationLocalServiceUtil.getLocation();
            request.setAttribute("location", location);

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
            .padded{
                padding: 10px;
            }
            -->
        </style>
        <jsp:include page="../inc_sxlightbox.jsp"/>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/Fin.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/AppMod.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>
        <script type="text/javascript">
            var store=null;
            Ext.onReady(function(){
                Ext.QuickTips.init();
                Ext.override(Ext.data.Connection, {
                        timeout:1000000
                });
                store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:['id','filename','size','url','datemodified'],
                        id:'id',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'daily.do',
                        method:'post'
                    }),
                    baseParams:{
                        start:0,
                        limit:20
                    },
                    autoLoad:true
                });
                function format(value){
                    return new Date(value.time);
                }
                var columns=[
                    {header:'File Name',dataIndex:'filename',
                        width:170,
                        renderer:function(value, metaData, record, rowIndex, colIndex, store) {
                            return '<b><a href="'+record.data.url+'">'+value+'</a></b>';
                        }},
                    {header:'Size',dataIndex:'size'},
                    {header:'Date Modified',dataIndex:'datemodified'}]
                Ext.onReady(function(){

                    var panel = new Ext.Panel({
            <%--width:GetWidth()-75,--%>
                            region:'center',
                            autoScroll:true,
                            layout:'fit',
                            items:[
                                new Ext.TabPanel({
                                    activeTab:0,
                                    //                                    height:600,
                                    items:[{
                                            title:'Daily Retrieve Report',
                                            layout:'fit',
                                            items:[
                                                new Ext.grid.GridPanel({
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
                                            ]
                                        }
                                    ]
                                })
                            ]
                        });
                        var menupanel=new Ext.Panel({
                            layout:'border',
                            width:GetWidth()-90,
                            height:600,
                            renderTo:'tabspane',
                            items:[
                                {
                                    split:true,
                                    title:'<a href="<%=getServletContext().getContextPath()%>/main.jsp">Main Menu</a>',
                                    layout:'accordion',
                                    collapsible:true,
                                    region:'west',
                                    width:'15%',
                                    defaults:{
                                        bodyStyle:'padding: 3px 3px 3px 3px',
                                        border:true
                                    },
                                    items:[
                                        {
                                            title:'Work Order',
                                            autoLoad:'<%= application.getContextPath()%>/menu?wo=true'
                                        },
                                        {
                                            title:'Vendor Management',
                                            autoLoad:'<%= application.getContextPath()%>/menu?proc=true'
                                        },
                                        {
                                            title:'Work Order Approval',
                                            autoLoad:'<%= application.getContextPath()%>/menu?app=true'
                                        },
                                        {
                                            title:'User Administration',autoLoad:'<%= application.getContextPath()%>/menu?adm=true'
                                        }

                                    ]
                                },panel
                            ]
                        });
                    });
                })
                
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
