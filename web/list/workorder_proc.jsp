<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    com.ckb.wo.client.model.UserBeans ub =
            (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (!ub.isLogon()) {
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
        }
        if (!com.ckb.wo.server.service.util.UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId())) {
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
            -->
        </style>
        <jsp:include page="../inc_sxlightbox.jsp"/>
        <jsp:include page="../scripts/include_ext.jsp"/>
        <script type="text/javascript">
            var store=null;
            var lastPage=0;
            Ext.onReady(function(){
                Ext.QuickTips.init();
                Ext.override(Ext.data.Connection, {
                        timeout:1000000
                });
                store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:['type','adhoc','action','nomorwo','origin','destination','vendor', 'vendor.vendorname','executiondate','charge','charges','totalweight','totalvolume','wostatus','revisionreason','editreason','cancelreason','editcounter','revisioncounter','validationnote','level.description','creatorUser', 'workOrderServiceModeDetail'],
                        id:'tworkorderPk',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'proc.do',
                        method:'post'
                    }),
                    baseParams:{
                        start:0,
                        limit:10
                    },
                    sortInfo: { field: 'vendor.vendorname', direction: 'ASC'} 
                });
                function format(value){
                    return new Date(value.time);
                }
                var columns=[
                    {header:'Action',dataIndex:'action',width:200},
                    {header:'wo#',dataIndex:'nomorwo',
                        width:170,
                        renderer:function(value, metaData, record, rowIndex, colIndex, store) {
                            return '<b>'+value+'</b>';
                        }}
                    ,{header:'Origin',dataIndex:'origin',renderer:function(value, metaData) {
                            metaData.attr = 'style="color:green"';
                            return value.locationname;
                        }
                    },
                    {header:'Destination',dataIndex:'destination',renderer:function(value, metaData) {
                            metaData.attr = 'style="color:darkblue"';
                            return value.locationname;
                        }}
                    ,{header:'WO Status',dataIndex:'wostatus',renderer:function(value,md){md.attr="style='text-align:right'";return value.replace("_"," ");}}
                    ,{header:'SMD Name',dataIndex:'workOrderServiceModeDetail', renderer:function(value, metaData){
                      var result = "";
                      if(value.length > 0 && value[0].servicemodeDetail !== null){
                          result = value[0].servicemodeDetail.smdname;
                      }
                      return result;   
                    }},
                    {header:'Charge',dataIndex:'charge',renderer:Ext.util.Format.numberRenderer('0,000.00'),hidden:true},
                    {header:'Charges',dataIndex:'charges'},
                    {header:'Vendor Name',width:250,dataIndex:'vendor',renderer:function(value){return value.vendorname;}}
                ];
                
                var panel = new Ext.Panel({
                    region:'center',
                    width:GetWidth()-75,
                    items:[
                        new Ext.TabPanel({
                            activeTab:${ param['t']==null ? "0" : param['t'] },
                            height:600,
                            items:[
                                {
                                    title:'Validate Work Order',
                                    layout:'fit',
                                    listeners:{
                                        activate:function(tab){
                                            store.setBaseParam("t", 'validate');
                                            load();
                                        }
                                    },
                                    items:[
                                        new Ext.grid.GridPanel({
                                            loadMask:true,
                                            store:store,
                                            columns:columns,
                                            tbar:[
                                                new Ext.PagingToolbar({
                                                    pageSize: 10,
                                                    store: store,
                                                    displayInfo: true,
                                                    displayMsg: 'Displaying {0} - {1} of {2} Records',
                                                    emptyMsg: "Empty"
                                   
                                                }),'-',
                                                new Ext.Toolbar({items:[
                                                        {
                                                            xtype:'textfield',
                                                            emptyText:'WO#',
                                                            fieldLabel:'WO#',
                                                            id:'search',
                                                            width:150
                                                        },'-',{
                                                            text:'Filters',
                                                            handler: function (e) { 
                                                                store.setBaseParam("t", "validate");
                                                                load();
                                                            }
                                                        },{
                                                            text:'Reset Filter',
                                                            handler:function(e){
                                                                Ext.getCmp('search').reset();
                                                                store.setBaseParam("t", 'validate');
                                                                load();
                                                            }
                                                        }
                                                    ]})
                                            ]
                                        })
                                    ]
                                },{
                                    title:'Validated Work Order',
                                    layout:'fit',
                                    listeners:{
                                        activate:function(tab){
                                            store.setBaseParam("t", 'validated');
                                            load();

                                        }
                                    },
                                    items:[
                                        new Ext.grid.GridPanel({
                                            loadMask:true,
                                            store:store,
                                            columns:columns,
                                            tbar:[
                                                new Ext.PagingToolbar({
                                                    pageSize: 10,
                                                    store: store,
                                                    displayInfo: true,
                                                    displayMsg: 'Displaying {0} - {1} of {2} Records',
                                                    emptyMsg: "Empty"
                                   
                                                }),'-'
                                            ]
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
            function load(){
                store.setBaseParam("wo",Ext.getCmp('search').getRawValue());
                store.load();
            }
            
            /**
            * function for load from child in validate.jsp
             * @returns {undefined}             */
            function loadLast(){
                store.removeAll();
                var lastP = Ext.getCmp("ext-comp-1005").value * 10;
                if(lastP === 10){
                   lastP = 0;
                }else{
                   lastP = lastP - 10;
                }
                store.load({
                    params: {
                        start: lastP,
                        limit: 10
                    }
                });
            }
            
            function viewWo(id,type){
                SeyLightbox.display('viewwo.jsp?wo='+id+'&t='+type+'&TB_iframe=1&width=700&height='+(GetHeight()-90));
            }
            function viewNotes(id){
                SeyLightbox.display('workorder_notes.jsp?wo='+id+'&TB_iframe=1&width=700&height='+(GetHeight()-90));
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
