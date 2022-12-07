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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" xmlns:wdg="http://ns.adobe.com/addt">
    <head>
        <title><%= application.getInitParameter("appTitle") %></title>
        <link rel="stylesheet" href="main.css" type="text/css" />
        <meta name="author" content="Chandra Adriansyah" />
        <meta name="programmer" content="Amaran Sidhiq" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
        <link rel="stylesheet" href="images/style.css" type="text/css" />
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
        <jsp:include page="inc_sxlightbox.jsp"/>
        <jsp:include page="scripts/include_ext.jsp"/>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/AppMod.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>

        <script type="text/javascript">
            var grantWin=null;
            Ext.onReady(function(){
                var panel=new Ext.Panel({
                    layout:'border',
                    width:GetWidth()-90,
                    height:GetHeight()-100,
                    renderTo:'entryDiv',
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
                                    autoLoad:'menu?wo=true'
                                },
                                {
                                    title:'Vendor Management',
                                    autoLoad:'menu?proc=true'
                                },
                                {
                                    title:'Work Order Approval',
                                    autoLoad:'menu?app=true'
                                },
                                {
                                    title:'User Administration',
                                    autoLoad:'menu?adm=true'
                                }

                            ]
                        },{
                            region:'center',
                            autoLoad:'welcome.jsp'
                        }
                    ]
                });
                
            });
            function openGrant(){
                if(grantWin===null){
                    var leave= null;
                    AppMod.getGrant({
                        callback:function(result){
                            leave=Ext.decode(result);
                        },
                        async:false
                    });
                    grantWin= new Ext.Window({
                        title:'Set Date for the Escalation',
                        closable:false,
                        modal:true,
                        resizable:false,
                        items:{
                            xtype:'form',
                            border:false,
                            items:[
                                {
                                    xtype:'datefield',
                                    fieldLabel:'Escalate From',
                                    id:'gFrom',
                                    value:leave.from
                                },
                                {
                                    xtype:'datefield',
                                    fieldLabel:'Escalate To',
                                    id:'gTo',
                                    value:leave.to
                                }
                            ]
                        },
                        buttons:[
                            {
                                text:'Escalate',
                                handler:function(e){
                                    AppMod.setGrant(Ext.getCmp('gFrom').getValue(),Ext.getCmp('gTo').getValue(),{
                                        callback:function(result){
                                            if(result){
                                                alert('Your approval has beed escalate!');
                                            }else{
                                                alert('Error escalating your approval!');
                                            }
                                        },async:false
                                    });
                                    grantWin.hide();
                                }
                            },
                            {
                                text:'Close',
                                handler:function(e){
                                    close();
                                }
                            }
                        ]
                    });
                }
                grantWin.show();
            }
            function close(){
                grantWin.close();
                grantWin=null;
            }
        </script>
    </head>
    <body>
        <div class="content">
            <!-- header -->
            <jsp:include page="header.jsp"/>
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
                                                    <table width="100%" border="0" >
                                                        <tr>
                                                            <td bgcolor="#FFFFFF">
                                                                <div id="entryDiv"></div>
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
        <jsp:include page="footer.jsp"/>
        <!-- end footer -->
    </body>
</html>
