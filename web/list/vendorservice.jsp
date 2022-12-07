<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
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
        <title><%= application.getInitParameter("appTitle") %></title>
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
            .tip-target {
                text-align:center;
                padding: 5px 0;
                color: #15428b;
                margin:10px;
                float:inherit;
            }
            -->
        </style>
        <jsp:include page="../inc_sxlightbox.jsp"/>

        <script src="../scripts/ext/ext-base.js" type="text/javascript"></script>
        <script src="../scripts/ext/ext-all.js" type="text/javascript"></script>
        <link rel="stylesheet" href="../scripts/ext/res/css/ext-all.css" type="text/css" media="print, projection, screen">
        <%--<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery.datepick.js"></script>--%>
        <script type="text/javascript">
            var store=null;
            Ext.onReady(function(){
                store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:[],
                        id:'tvendorservicePk',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'vservice.do',
                        method:'post'
                    }),
                    baseParams:{
                        start:0,
                        limit:20
                    }

                });

                var panel = new Ext.Panel({
                    applyTo:'tabspane',
                    width:GetWidth()-75,
                    items:[
                        new Ext.TabPanel({
                            activeTab:${ param['t']==null ? "0" : param['t'] },
                            height:600,
                            items:[
                                {
                                    title:'Vendor Services (Charter)',
                                    layout:'fit',
                                    listeners:{
                                        activate:function(tab){
                                            store.setBaseParam("t", 1);
                                            load();

                                        }
                                    },
                                    items:[
                                        new Ext.grid.GridPanel({
                                            loadMask:true,
                                            store:store,
                                            columns:[
                                                {header:'Action',dataIndex:'action',width:200},
                                                {header:'wo#',dataIndex:'nomorwo'},
                                                {header:'Origin',dataIndex:'origin'},
                                                {header:'Destination',dataIndex:'destination'},
                                                {header:'Vendor Name',dataIndex:'vendor'},
                                                {header:'Execution Date',dataIndex:'executiondate'}
                                            ],
                                            tbar:new Ext.PagingToolbar({
                                                pageSize: 20,
                                                store: store,
                                                displayInfo: true,
                                                displayMsg: 'Displaying {0} - {1} of {2} Records',
                                                emptyMsg: "Empty"
                                   
                                            })

                                        })
                                    ]
                                },{
                                    title:'Vendor Services (Chargeable Weight)',
                                    layout:'fit',
                                    listeners:{
                                        activate:function(tab){
                                            store.setBaseParam("t", 2);
                                            load();

                                        }
                                    },
                                    items:[
                                        new Ext.grid.GridPanel({
                                            loadMask:true,
                                            store:store,
                                            columns:[
                                                {header:'Action',dataIndex:'action',width:200},
                                                {header:'wo#',dataIndex:'nomorwo'},
                                                {header:'Origin',dataIndex:'origin'},
                                                {header:'Destination',dataIndex:'destination'},
                                                {header:'Vendor Name',dataIndex:'vendor'},
                                                {header:'Execution Date',dataIndex:'executiondate'}
                                            ],
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
                
            });
            function load(){
                store.setBaseParam("search",Ext.getCmp('search').getRawValue());
                store.setBaseParam("dateFrom", Ext.getCmp('dtFrom').getRawValue());
                store.setBaseParam("dateTo", Ext.getCmp('dtTo').getRawValue());
                store.load();
            }
            function viewWo(id){
                SeyLightbox.display('viewwo.jsp?wo='+id+'&t='+type+'&TB_iframe=1&width=700&height='+(GetHeight()-90));
            }
            <%--$(function(){
                $("#from").datepick({
                    dateFormat: 'MM d, yy',
                    altField: '#hfFrom',
                    altFormat: 'yy-mm-dd',
                    selectDefaultDate: true
                });
                $("#to").datepick({
                    dateFormat: 'MM d, yy',
                    altField: '#hfTo',
                    altFormat: 'yy-mm-dd',
                    selectDefaultDate: true
                });
                $('#bFilter').click(load);
                $('#reset').click(function(){
                    $('#hfFrom').val('');
                    $('#hfTo').val('');
                    load();
                });
            });--%>
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
