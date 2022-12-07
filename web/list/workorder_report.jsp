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
                store=new Ext.data.Store({
                    reader: new Ext.data.JsonReader({
                        fields:['adhoc','action','nomorwo','origin','destination','vendor','executiondate','charge','charges','type','totalweight','totalvolume','wostatus','revisionreason','editreason','cancelreason','validationnote','editcounter','revisioncounter','manifestda','maxlevel','lastactiondate','lastactionby','nextapproval','leadtimenextapp','woleadtime','woperformance'],
                        id:'tworkorderPk',
                        totalProperty:'total',
                        root:'data'
                    }),
                    proxy: new Ext.data.HttpProxy({
                        url:'woreport.do',
                        method:'post'
                    }),
                    baseParams:{
                        start:0,
                        limit:20
                    }
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
                        }},
                    {header:'Origin',dataIndex:'origin',renderer:function(value, metaData) {
                            metaData.attr = 'style="color:green"';
                            return value.locationname;
                        }
                    },
                    {header:'Destination',dataIndex:'destination',renderer:function(value, metaData) {
                            metaData.attr = 'style="color:darkblue"';
                            return value.locationname;
                        }},
                    {header:'Type',dataIndex:'type'},
                    {header:'Ad-Hoc',dataIndex:'adhoc',renderer:function(v,m){return v?"Yes":"No"}},
                    {header:'Charge',dataIndex:'charge',renderer:Ext.util.Format.numberRenderer('0,000.00'),hidden:true},
                    {header:'Charges',dataIndex:'charges'},
                    {header:'Vendor Name',width:250,dataIndex:'vendor',renderer:function(value){return value.vendorname}},
                    {header:'Execution Date',dataIndex:'executiondate',renderer:function(value){return new Date(value.time).format("F d, Y");}},
                    {header:'Total Weight',dataIndex:'totalweight',renderer:function(value,md){md.attr="style='text-align:right'";return value;}},
                    {header:'Total Dimension',dataIndex:'totalvolume',renderer:function(value,md){md.attr="style='text-align:right'";return value;}},
                    {header:'WO Status',dataIndex:'wostatus',renderer:function(value,md){md.attr="style='text-align:right'";return value.replace("_"," ");}},
                    {header:'Revision Reason',dataIndex:'revisionreason',
                        renderer:function(value,md){
                            md.attr="ext:qtip='"+value+"' ext:qtitle='Revisio Reason'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length>20?20:fv.length)+"...";
                        }},
                    {header:'Edit Reason',dataIndex:'editreason',
                        renderer:function(value,md){
                            md.attr="ext:qtip='"+value+"' ext:qtitle='Edit Reason'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length>20?20:fv.length)+"...";
                        }
                    },
                    {header:'Validation Note',dataIndex:'validationnote',
                        renderer:function(value,md){
                            md.attr="ext:qtip='"+value+"' ext:qtitle='validationnote'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length>20?20:fv.length)+"...";
                        }
                    },
                    {header:'Cancel Reason',dataIndex:'cancelreason',
                        renderer:function(value,md){
                            md.attr="ext:qtip='"+value+"' ext:qtitle='Cancel Reason'";
                            var fv = Ext.util.Format.stripTags(value);
                            return fv.substr(0, fv.length>20?20:fv.length)+"...";
                        }},
                    {header:'Edit#',dataIndex:'editcounter',hidden:true},
                    {header:'Revision#',dataIndex:'revisioncoutner',hidden:true},
                    //                    {header:'Edit#',dataIndex:'validationnote'},
                    {header:'Manifest/DA',dataIndex:'manifestda'},
                    {header:'Final Level of Approval',dataIndex:'maxlevel'},
                    {header:'Last Action Date',dataIndex:'lastactiondate'},
                    {header:'Last Action By',dataIndex:'lastactionby'},
                    {header:'Next Approval',dataIndex:'nextapproval'},
                    {header:'Lead Time To Next Approval',dataIndex:'leadtimenextapp'},
                    {header:'Lead Time',dataIndex:'woleadtime'},
                    {header:'Performance',dataIndex:'woperformance'}
                ]
                Ext.onReady(function(){

                    var panel = new Ext.Panel({
            <%--width:GetWidth()-75,--%>
                            region:'center',
                            autoScroll:true,
                            layout:'fit',
                            items:[
                                new Ext.TabPanel({
                                    activeTab:${ param['t']==null ? "0" : param['t'] },
                                    //                                    height:600,
                                    items:[{
                                            title:'Report Generator',
                                            items:
                                                {
                                                xtype:'form',
                                                id:'frmReport',
                                                border:false,
                                                items:[
                                                    {
                                                        layout:'table',
                                                        layoutConfig:{
                                                            columns:3
                                                        },
                                                        defaults:{
                                                            cellCls:'padded',
                                                            layout:'form',
                                                            xtype:'panel',
                                                            border:false
                                                        },
                                                        items:[{
                                                                items:[
                                                                    {
                                                                        xtype:'textfield',
                                                                        fieldLabel:'WO#',
                                                                        id:'search',
                                                                        width:150
                                                                    },{
                                                                        xtype:'datefield',
                                                                        fieldLabel:'From',
                                                                        format:'Y-m-d',
                                                                        id:'dtFrom',
                                                                        width:150
                                                                    },{
                                                                        xtype:'datefield',
                                                                        fieldLabel:'To',
                                                                        format:'Y-m-d',
                                                                        id:'dtTo',
                                                                        width:150
                                                                    }
                                                                ]
                                                            },{
                                                                items:[
                                                                    {
                                                                        xtype:'combo',
                                                                        name:'createdby',
                                                                        id:'createdby',
                                                                        fieldLabel:'Created By',
                                                                        typeAhead: true,
                                                                        width:250,
                                                                        forceSelection:false,
                                                                        triggerAction: 'all',
                                                                        mode: 'local',
                                                                        hiddenName:'eid',
                                                                        store: new Ext.data.Store({
                                                                            proxy:new Ext.data.HttpProxy({
                                                                                url:'woreport.do',
                                                                                method:'post'
                                                                            }),
                                                                            reader:new Ext.data.JsonReader({
                                                                                fields:['employee_id','fullname'],
                                                                                id:'employee_id',
                                                                                root:'data'
                                                                            }),
                                                                            baseParams:{
                                                                                r:'creator'
                                                                            },
                                                                            autoLoad:true
                                                                        }),
                                                                        valueField: 'employee_id',
                                                                        displayField: 'fullname',
                                                                        emptyText:'Creator Name'
                                                                    },{
                                                                        xtype:'combo',
                                                                        name:'vendors',
                                                                        id:'vendors',
                                                                        fieldLabel:'Vendor Name',
                                                                        typeAhead: true,
                                                                        width:250,
                                                                        forceSelection:false,
                                                                        triggerAction: 'all',
                                                                        mode: 'local',
                                                                        hiddenName:'vid',
                                                                        store: new Ext.data.Store({
                                                                            proxy:new Ext.data.HttpProxy({
                                                                                url:'woreport.do',
                                                                                method:'post'
                                                                            }),
                                                                            reader:new Ext.data.JsonReader({
                                                                                fields:['vendorpk','vendorname'],
                                                                                id:'vendorpk',
                                                                                root:'data'
                                                                            }),
                                                                            baseParams:{
                                                                                r:'vendor'
                                                                            },
                                                                            autoLoad:true
                                                                        }),
                                                                        valueField: 'vendorpk',
                                                                        displayField: 'vendorname',
                                                                        emptyText:'Vendor Name'
                                                                    },{
                                                                        xtype:'combo',
                                                                        name:'origins',
                                                                        id:'origins',
                                                                        fieldLabel:'Origin',
                                                                        typeAhead: true,
                                                                        width:250,
                                                                        forceSelection:false,
                                                                        triggerAction: 'all',
                                                                        mode: 'local',
                                                                        hiddenName:'olid',
                                                                        store: new Ext.data.Store({
                                                                            proxy:new Ext.data.HttpProxy({
                                                                                url:'woreport.do',
                                                                                method:'post'
                                                                            }),
                                                                            reader:new Ext.data.JsonReader({
                                                                                fields:['locpk','locationname'],
                                                                                id:'locpk',
                                                                                root:'data'
                                                                            }),
                                                                            baseParams:{
                                                                                r:'location'
                                                                            },
                                                                            autoLoad:true
                                                                        }),
                                                                        valueField: 'locpk',
                                                                        displayField: 'locationname',
                                                                        emptyText:'Origin'
                                                                    }
                                                                ]
                                                            },{
                                                                items:[
                                                                    {
                                                                        xtype:'combo',
                                                                        name:'destionations',
                                                                        id:'destionations',
                                                                        fieldLabel:'Destination',
                                                                        typeAhead: true,
                                                                        width:250,
                                                                        forceSelection:false,
                                                                        triggerAction: 'all',
                                                                        mode: 'local',
                                                                        hiddenName:'dlid',
                                                                        store: new Ext.data.Store({
                                                                            proxy:new Ext.data.HttpProxy({
                                                                                url:'woreport.do',
                                                                                method:'post'
                                                                            }),
                                                                            reader:new Ext.data.JsonReader({
                                                                                fields:['locpk','locationname'],
                                                                                id:'locpk',
                                                                                root:'data'
                                                                            }),
                                                                            baseParams:{
                                                                                r:'location'
                                                                            },
                                                                            autoLoad:true
                                                                        }),
                                                                        valueField: 'locpk',
                                                                        displayField: 'locationname',
                                                                        emptyText:'Destination'
                                                                    },{
                                                                        xtype:'combo',
                                                                        name:'txstatus',
                                                                        id:'txstatus',
                                                                        fieldLabel:'Work Order Status',
                                                                        typeAhead: true,
                                                                        //                                                        forceSelection:false,
                                                                        triggerAction: 'all',
                                                                        mode: 'local',
                                                                        hiddenName:'status',
                                                                        store: new Ext.data.ArrayStore({
                                                                            id: 0,
                                                                            fields: [
                                                                                'id',
                                                                                'text'
                                                                            ],
                                                                            data: [
                                                                                ['<%=com.ckb.wo.client.model.WorkOrder.APPROVED_STATUS%>', 'Approved'],
                                                                                ['<%=com.ckb.wo.client.model.WorkOrder.CANCELLED_STATUS%>', 'Cancelled'],
                                                                                ['<%=com.ckb.wo.client.model.WorkOrder.EDIT_STATUS%>', 'Edit'],
                                                                                ['<%=com.ckb.wo.client.model.WorkOrder.REVISION_STATUS%>', 'Revision'],
                                                                                ['<%=com.ckb.wo.client.model.WorkOrder.INPROGRESS_STATUS%>', 'In Progress']
                                                                            ]
                                                                        }),
                                                                        valueField: 'id',
                                                                        displayField: 'text',
                                                                        emptyText:'Select Status'
                                                                    }
                                                                ]
                                                            },
                                                            {
                                                                xtype:'panel',
                                                                layout:'table',
                                                                colspan:3,
                                                                layoutConfig:{
                                                                    columns:3
                                                                },
                                                                defaults:{
                                                                    width:100,
                                                                    cellCls:'padded'
                                                                },
                                                                items:[{
                                                                        xtype:'button',
                                                                        handler:load,
                                                                        text:'Preview'
                                                                    },{
                                                                        xtype:'button',
                                                                        handler:function(){
                                                                            location.href='';
                                                                        },
                                                                        text:'Save'
                                                                    },{
                                                                        xtype:'button',
                                                                        handler:function(){Ext.getCmp('frmReport').getForm().reset();},
                                                                        text:'Reset All Fields'
                                                                    }]
                                                            }
                                                        ]
                                                    }]
                                            }
                                        },{
                                            title:'Report Result',
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
                function load(){
                    store.setBaseParam("search",Ext.getCmp('search').getRawValue());
                    store.setBaseParam("dateFrom", Ext.getCmp('dtFrom').getRawValue());
                    store.setBaseParam("dateTo", Ext.getCmp('dtTo').getRawValue());
                    store.load();
                }
                function viewWo(id,type){
                    SeyLightbox.display('viewwo.jsp?wo='+id+'&t='+type+'&TB_iframe=1&width=700&height='+(GetHeight()-90));
                }
                function receive(twok){
                    var c = confirm('Are you sure?');
                    if(c){
                        Fin.receive(twok,{
                            callback:function(result){
                                if(result){
                                    alert("Selected Work Order Has been received!");
                                }else{
                                    alert("Selected Work Order cannot be received!");
                                }
                            },
                            async:false
                        });
                        load();
                    }
                }
                function validate(id){
                    var c = confirm("Validate Now?");
                    if(c){
                        AppMod.validate(id,{
                            callback:function(result){
                                alert(result);
                                load();
                            },async:false
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
