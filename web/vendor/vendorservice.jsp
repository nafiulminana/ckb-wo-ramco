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
        <jsp:include page="../scripts/include_ext.jsp"/>
        <script type="text/javascript">
            Ext.onReady(function(){
                var viframe = Ext.DomHelper.append(document.body, {
                    tag: 'iframe',
                    frameBorder: 0,
                    src: '${pageContext.servletContext.contextPath}/vendorservice',
                    width: '100%',
                    height: '100%'
                });

                var panel=new Ext.Panel({
                    layout:'border',
                    width:GetWidth()-90,
                    height:GetHeight()-100,
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
                        },{
                            region:'center',
                            contentEl:viframe,
                            height:600<%
                                if(com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.MANAGE_RATE)){
                                    out.print(",tbar:[{text:'Add New',handler:function(e){viframe.src='"+application.getContextPath()+"/vendorservice?act=add';}}," 
                                            //+ "{text:'Import From Excel',handler:function(e){viframe.src='"+application.getContextPath()+"/vendorservicefileuploadservlet';}},"
                                            + "{text:'Import Rate From Excel',handler:function(e){viframe.src='"+application.getContextPath()+"/VendorServiceFileUploadRateServlet';}}]");
                                }
                            %>
                        }
                    ]
                });

                function doSearch(s){
                    store.setBaseParam("search", s);
                    store.load();
                }
            });
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
                                                            <td id="tabspane">
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
