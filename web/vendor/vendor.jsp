<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }
                if(!com.ckb.wo.server.service.util.UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId())){
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
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/Vendor.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>

        <script type="text/javascript">
            var store=null;
            Ext.onReady(function(){
                store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:[
                            'action','vendorcode','vendorname','address1','address2','postalcode','website','email',
                            'contactname','contactphone','contactfax','contactemail','terms','flagGst',
                            'vendornpwp','glNo','remittanceaddress','vendor_owner'
                        ],
                        id:'tvendorPk',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'vendor.do',
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

                var tPanel = new Ext.Panel({
                    region:'center',
                    width:GetWidth()-75,
                    height:600,
                    title:'List Vendor',
                    layout:'fit',
                    items:[
                        new Ext.grid.GridPanel({
                            loadMask:true,
                            store:store,
                            columns:[
                                {header:'Action',dataIndex:'action',width:100},
                                {header:'Vendor#',dataIndex:'vendorcode'},
                                {header:'Vendor Name',dataIndex:'vendorname'},
                                {header:'Address 1',dataIndex:'address1'},
                                {header:'Address 2',dataIndex:'address2'},
                                {header:'Postal',dataIndex:'postalcode'},
                                {header:'Website',dataIndex:'website'},
                                {header:'Email',dataIndex:'email'},
                                {header:'Contact Name',dataIndex:'contactname'},
                                {header:'Contact Phone',dataIndex:'contactphone'},
                                {header:'Contact Fax',dataIndex:'contactfax'},
                                {header:'Contact Email',dataIndex:'contactemail'},
                                {header:'Terms',dataIndex:'terms',hidden:true},
                                {header:'Gst',dataIndex:'flagGst',hidden:true},
                                {header:'Vendor NPWP',dataIndex:'vendornpwp',hidden:true},
                                {header:'Gl#',dataIndex:'glNo'},
                                {header:'Remittance Address',dataIndex:'remittanceaddress',hidden:true},
                                {header:'Vendor Owner',dataIndex:'vendor_owner',hidden:true}
                            ],
                            tbar:new Ext.PagingToolbar({
                                pageSize: 20,
                                store: store,
                                displayInfo: true,
                                displayMsg: 'Displaying {0} - {1} of {2} Records',
                                emptyMsg: "Empty",
                                items:[
                                    '-',
                                    {
                                        xtype:'textfield',
                                        id:'search',
                                        enableKeyEvents:true,
                                        listeners:{
                                            keypress:function(textField,evt){
                                                if(evt.getKey()===Ext.EventObject.ENTER){
                                                    doSearch(textField.getValue());
                                                }
                                            }
                                        }
                                    },{
                                        text:'search',
                                        handler:function(e){
                                            doSearch(Ext.getCmp('search').getValue());
                                        }
                                    },'-',{
                                        text:'Add New Vendor',
                                        handler:function(e){
                                            SeyLightbox.display('vendor_new.jsp?TB_iframe=1&width=700&height='+(getHeight()-90)+'&modal=1&title=Add New Vendor');
                                        }
                                    }
                                ]
                            })

                        })
                    ]
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
                        },tPanel
                    ]
                });
            });
            function doSearch(s){
                store.setBaseParam("search", s);
                store.load();
            }
            function setActive(p){
                Vendor.setActive(p,{
                    callback:function(result){
                        alert(result);
                        doSearch('');
                    },async:false
                });
            }
            var dWin=null;
            function setDeactive(p){
                if(dWin===null){
                    dWin = new Ext.Window({
                        title:'Set Inactive',
                        closable:false,
                        modal:true,
                        items:{
                            xtype:'form',
                            items:[
                                {xtype:'datefield',id:'from',fieldLabel:'From',format:'Y-m-d'},
                                {xtype:'datefield',id:'to',fieldLabel:'To',format:'Y-m-d'},
                                {xtype:'textarea',id:'reason',fieldLabel:'Reason',width:200}
                            ]
                        },
                        buttons:[
                            {
                                text:'Deactivate',
                                handler:function(e){
                                    Vendor.setDeactive(p,Ext.getCmp('reason').getValue(),Ext.getCmp('from').getValue(),Ext.getCmp('to').getValue(),{
                                        callback:function(result){
                                            alert(result);
                                            doSearch('');
                                            close();
                                        },async:false
                                    });
                                }
                            },
                            {
                                text:'Close',
                                handler:close
                            }
                        ]
                    });
                    dWin.show();
                }
            }
            function close(){
                if(dWin!==null){
                    dWin.close();
                    dWin=null;
                }
            }
            function edit(pk){
                SeyLightbox.display('vendor_new.jsp?e='+pk+'&TB_iframe=1&width=700&height='+(getHeight()-90)+'&modal=1&title=Edit Vendor');
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
