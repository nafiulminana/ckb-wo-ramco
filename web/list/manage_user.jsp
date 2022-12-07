<%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }
                if (!com.ckb.wo.server.service.util.PermissionLocalServiceUtil.doesUserHavePermission(ub.getEmployeeId(), com.ckb.wo.client.model.Permission.MANAGE_USER)) {
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
        <link rel="stylesheet" href="<%= application.getContextPath()%>/images/style.css" type="text/css" />
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
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/interface/User.js'></script>
        <script type='text/javascript' src='${pageContext.servletContext.contextPath}/dwr/engine.js'></script>

        <script type="text/javascript">
            var listStore=new Ext.data.Store({
                reader:new Ext.data.JsonReader({
                    fields:['action',
                        'employeeId','firstName','middleName','lastName','grantfrom','grantto','bossemployeeid','boss',
                        'email','areaId','divisionId','departmentId','stationId','jobTitleId','levelId','isactiveuser'
                    ],
                    id:'employeeId',
                    totalProperty:'total',
                    root:'data'
                }),
                proxy: new Ext.data.HttpProxy({
                    url:'user.do',
                    method:'post'
                }),
                baseParams:{
                    t:'user',
                    n:'',
                    start:0,
                    limit:20
                },
                autoLoad:true
            });
            var role = new Ext.data.Store({
                reader:new Ext.data.JsonReader({
                    fields:['trolePk','rolename'],
                    root:'data',
                    id:'trolePk'
                }),
                proxy:new Ext.data.HttpProxy({
                    url:'role.do',
                    method:'post'
                }),
                autoLoad:false
            });
            function load(n){
                listStore.setBaseParam("n",n);
                listStore.load();
                Ext.getCmp('formDetail').getForm().reset();
                Ext.getCmp('super').setText("",true);
                role.clearData();
            }
            Ext.onReady(function(){
                var accessStore=new Ext.data.Store({
                    reader:new Ext.data.JsonReader({
                        fields:['menuId','menu','status'],
                        id:'menuId',
                        root:'data'
                    }),
                    proxy:new Ext.data.HttpProxy({
                        url:'UserList'
                    }),
                    autoload:true
                });
                var panel=new Ext.Panel({
                    layout:'border',
                    width:GetWidth()-90,
                    height:500,
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
                                    title:'User Administration',
                                    autoLoad:'<%= application.getContextPath()%>/menu?adm=true'
                                }

                            ]
                        },{
                            region:'center',
                            border:false,
                            layout:'form',
                            autoScroll:true,
                            items:[
                                new Ext.grid.GridPanel({
                                    title:'Activated User List',
                                    id:'userList',
                                    height:250,
                                    frame:false,
                                    singleSelect:true,
                                    store:listStore,
                                    columns:[
                                        {header:'Action',dataIndex:'action'},{header:'Employee Id',dataIndex:'employeeId'},{header:'First Name',dataIndex:'firstName'},{header:'Middle Name',dataIndex:'middleName'},{header:'Last Name',dataIndex:'lastName'},{header:'Escalate From',dataIndex:'grantfrom',renderer:function(v,m){return v==null?"":new Date(v.time).format("F d, Y");}},{header:'Escalate To',dataIndex:'grantto',renderer:function(v,m){return v==null?"":new Date(v.time).format("F d, Y");}},{header:'Superior',dataIndex:'boss'},
                                        {header:'Email',dataIndex:'email'},{header:'Area',dataIndex:'areaId'},{header:'Division',dataIndex:'divisionId'},{header:'Department',dataIndex:'departmentId'},{header:'Station',dataIndex:'stationId'},{header:'Job Title',dataIndex:'jobTitleId'},{header:'Level',dataIndex:'levelId'},{header:'Active',dataIndex:'isactiveuser',renderer:function(v){return v?"Active":"Inactive"}}
                                    ],
                                    tbar:
                                        new Ext.PagingToolbar({
                                        pageSize: 20,
                                        store: listStore,
                                        listeners:{
                                            change:function(p, d){
                                                role.clearData();
                                            }
                                        },
                                        displayInfo: true,
                                        displayMsg: 'Displaying {0} - {1} of {2} Records',
                                        emptyMsg: "Empty",
                                        items:["-",
                                            {
                                                xtype:'textfield',
                                                emptyText:'Employee Name',
                                                id:'empName'
                                            },
                                            {
                                                text:'Find',
                                                toolTip:{
                                                    title:'Search Employe',
                                                    text:'Search employee that match the name'
                                                },
                                                handler:function(){
                                                    load(Ext.getCmp('empName').getValue());
                                                }

                                            },{
                                                text:'Clear Filter',
                                                toolTip:{
                                                    title:'Reset Filter',
                                                    text:'Reset name filter.'
                                                },
                                                handler:function(){
                                                    load("");
                                                }

                                            },{
                                                text:'Add New User',
                                                handler:function(e){
                                                    SeyLightbox.display('fast_user.jsp?TB_iframe=1&width=700&height='+(GetHeight()-90));
                                                }
                                            }]
                                    })
                                    ,
                                    sm: new Ext.grid.RowSelectionModel({
                                        singleSelect:true
                                    }),
                                    listeners:{
                                        rowclick:function(g,i,e){
                                            var record=g.getSelectionModel().getSelected().data;
                                            Ext.getCmp('employeeId').setValue(record.employeeId);
                                            Ext.getCmp('employeeName').setValue(record.firstName+(record.middleName.length<1?" ":" "+record.middleName+" ")+record.lastName);
                                            Ext.getCmp('super').setText(record.boss,false);
                                            Ext.getCmp('station').setValue(record.stationId);
                                            role.clearData();
                                            role.load({
                                                params:{
                                                    u:record.employeeId
                                                },
                                                callback:function(r,o,s){
                                                    Ext.getCmp("btnAssign").enable();
                                                }
                                            })
                                        }
                                    }
                                }),
                                {
                                    xtype:'fieldset',
                                    id:'detail',
                                    title:'Employee Information',
                                    autoHeight:true,
                                    layout:'table',
                                    layoutConfig:{
                                        column:2
                                    },
                                    items:[
                                        {
                                            xtype:'form',
                                            id:'formDetail',
                                            bodyStyle:'padding: 10px 10px 10px 10px',
                                            border:false,
                                            items:[
                                                {
                                                    xtype:'textfield',
                                                    readOnly:true,
                                                    id:'employeeId',
                                                    fieldLabel:'Employee ID',
                                                    width:50
                                                },{
                                                    xtype:'textfield',
                                                    readOnly:true,
                                                    id:'employeeName',
                                                    fieldLabel:'Employee Name',
                                                    width:200
                                                },{
                                                    xtype:'label',
                                                    fieldLabel:"Superior",
                                                    id:'super'
                                                },
                                                {
                                                    xtype:'textfield',
                                                    readOnly:true,
                                                    id:'station',
                                                    fieldLabel:'Station',
                                                    width:50
                                                }]
                                        },{
                                            layout:'fit',
                                            title:'User Roles',
                                            width:400,
                                            height:200,
                                            tbar:[{
                                                    id:'btnAssign',disabled:true,
                                                    text:'Assign New Role',handler:function(e){
                                                        showRole();
                                                    }}],
                                            items:new Ext.list.ListView({
                                                store: role,
                                                emptyText: 'No Role Assigned!',
                                                columns: [{
                                                        header: 'Role',
                                                        dataIndex: 'rolename'
                                                    }]
                                            })
                                        }
                                    ]
                                }
                            ]

                        }
                    ]
                });
            });
            wRole=null;
            function showRole(){
                if(wRole==null){
                    wRole = new Ext.Window({
                        title:'Select Role',
                        layout:'fit',
                        closable:false,
                        modal:true,
                        width:300,height:200,
                        items:{xtype:'panel',bodyStyle:'background-color:white',items:new Ext.list.ListView({
                                id:'lRole',
                                store: new Ext.data.Store({
                                    reader:new Ext.data.JsonReader({
                                        fields:['trolePk','rolename'],
                                        root:'data',
                                        id:'trolePk'
                                    }),
                                    proxy:new Ext.data.HttpProxy({
                                        url:'role.do',
                                        method:'post'
                                    }),
                                    autoLoad:true
                                }),
                                multiSelect: true,
                                emptyText: 'No Role Available!',
                                columns: [{
                                        header: 'Role',
                                        dataIndex: 'rolename'
                                    }]
                            })},
                        buttons:[
                            {
                                text:'Assign',handler:function(e){
                                    var r = Ext.getCmp('lRole').getSelectedRecords();
                                    var x=[];
                                    for(var i=0; i<r.length;i++){
                                        x[i]=(r[i].data.trolePk);
                                    }
                                    if(x.length>0){
                                        User.assign(Ext.getCmp('employeeId').getValue(),x,{
                                            callback:function(e){
                                                alert(e);
                                                wRole.close();
                                                wRole=null;
                                                role.load({
                                                    params:{
                                                        u:Ext.getCmp('employeeId').getValue()
                                                    },
                                                    callback:function(r,o,s){
                                                        if(!s){
                                                            role.clearData();
                                                        }
                                                    }
                                                });
                                            },
                                            async:false
                                        });
                                    }else{
                                        alert('Role not selected!');
                                    }
                                }
                            },
                            {
                                text:'Close',handler:function(e){
                                    wRole.close();
                                    wRole=null;
                                    
                                }
                            }
                        ]
                    });
                }
                wRole.show();
            }
            function setActive(pk,active){
                User.setActive(pk,active,{
                    callback:function(result){
                        alert(result);
                        load();
                    },async:false
                });
            }
            function removeRole(pk,rrole){
                User.removeRole(pk,rrole,{
                    callback:function(result){
                        alert(result);
                        role.load({
                            params:{
                                u:Ext.getCmp('employeeId').getValue()
                            },
                            callback:function(r,o,s){
                                if(!s){
                                    role.clearData();
                                }
                            }
                        })
                    },async:false
                });
            }
            function setBoss(pk){
                SeyLightbox.display('fast_user.jsp?sb='+pk+'TB_iframe=1&width=700&height='+(GetHeight()-90));
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
        <jsp:include page="../footer.jsp"/>
        <!-- end footer -->
    </body>
</html>
