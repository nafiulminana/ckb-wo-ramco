
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/ext/ext-all.js"></script>
<script type="text/javascript">
/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

    /**
     * Handler specified for the 'Available' column renderer
     * @param {Object} value
     */
    function formatDate(value){
        return value ? value.dateFormat('M d, Y') : '';
    }

    // shorthand alias
    var fm = Ext.form;

    Ext.util.Format.comboRenderer = function(combo){
        return function(value){
            //alert(value);
            var record = combo.findRecord(combo.valueField, value);
            return record ? record.get(combo.displayField) : combo.valueNotFoundText; //
        }
    };

    // the check column is created using a custom plugin
   var comboVendor = new fm.ComboBox({
                            typeAhead: true,
                            triggerAction: 'all',
                            // transform the data already specified in html
                            transform: 'tvendorPk',

                            lazyRender: true,
                            listClass: 'x-combo-list-small'
                        });
    // the column model has information about grid columns
    // dataIndex maps the column to the specific data field in
    // the data store (created below)
    var cm = new Ext.grid.ColumnModel({
        // specify any defaults for each column
        defaults: {
            sortable: true // columns are not sortable by default
        },
        columns: [
            {
                id: 'tvendorservicePk',
                header: 'Id',
                dataIndex: 'tvendorservicePk',
                width: 20,
                // use shorthand alias defined above
                editor: new fm.TextField({
                    allowBlank: false
                })
            }, {
                header: 'vendor',
                dataIndex: 'tvendorFk',
                width: 130,
                editor: comboVendor,
                renderer:  Ext.util.Format.comboRenderer(comboVendor)
            }, {
                header: 'rate',
                dataIndex: 'rate',
                width: 130,
                editor: new fm.TextField({
                    allowBlank: false
                })
            }, {
                header: 'Agreement No',
                dataIndex: 'refagreementno',
                width: 70,
                align: 'right',
                editor: new fm.TextField({
                    allowBlank: false
                })
            }
            //checkColumn // the plugin instance
        ]
    });

    // create the Data Store
		var store = new Ext.data.JsonStore({
			// store configs
			autoDestroy: true,
			autoLoad: true,
			url: '<%=getServletContext().getContextPath()%>/vendorservice?getdata=true',
			// reader configs
			idProperty: 'tvendorservicePk',
			fields: ['tvendorservicePk','tvendorFk','vendor.vendorname', 'weightfrom', 'weightto','rate', {name:'validto', type:'date'}]
		});


    // create the editor grid
    var grid = new Ext.grid.EditorGridPanel({
        store: store,
        cm: cm,
        renderTo: 'editor-grid',
        width: 600,
        height: 300,
        //autoExpandColumn: 'tvendorservicePk', // column with this id will be expanded
        title: 'Edit Plants?',
        frame: true,
        // specify the check column plugin on the grid so the plugin is initialized
        //plugins: checkColumn,
        clicksToEdit: 1

    });


});

</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>

    <select name="tvendorPk" id="tvendorPk" style="display: none;">
        <c:forEach items="${requestScope.vendors}" var="vendor">
    <option value="${vendor.tvendorPk}">${vendor.vendorname}</option>
  </c:forEach>
    </select>

     <div id="editor-grid"></div>

    </body>
</html>
