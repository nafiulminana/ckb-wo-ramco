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

    // the check column is created using a custom plugin

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
                width: 220,
                // use shorthand alias defined above
                editor: new fm.TextField({
                    allowBlank: false
                })
            }, {
                header: 'rate',
                dataIndex: 'rate',
                width: 130,
                editor: new fm.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    // transform the data already specified in html
                    transform: 'light',
                    lazyRender: true,
                    listClass: 'x-combo-list-small'
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
    fields: ['tvendorservicePk', 'weightfrom', 'weightto','rate', {name:'validto', type:'date'}]
});


    // create the editor grid
    var grid = new Ext.grid.EditorGridPanel({
        store: store,
        cm: cm,
        renderTo: 'editor-grid',
        width: 600,
        height: 300,
        autoExpandColumn: 'tvendorservicePk', // column with this id will be expanded
        title: 'Edit Plants?',
        frame: true,
        // specify the check column plugin on the grid so the plugin is initialized
        //plugins: checkColumn,
        clicksToEdit: 1,
        tbar: [{
            text: 'Add Plant',
            handler : function(){
                // access the Record constructor through the grid's store
                var Plant = grid.getStore().recordType;
                var p = new Plant({
                    common: 'New Plant 1',
                    light: 'Mostly Shade',
                    price: 0,
                    availDate: (new Date()).clearTime(),
                    indoor: false
                });
                grid.stopEditing();
                store.insert(0, p);
                grid.startEditing(0, 0);
            }
        }]
    });

    // manually trigger the data store load
    store.load({
        // store loading is asynchronous, use a load listener or callback to handle results
        callback: function(){
            Ext.Msg.show({
                title: 'Store Load Callback',
                msg: 'store was loaded, data available for processing',
                modal: false,
                icon: Ext.Msg.INFO,
                buttons: Ext.Msg.OK
            });
        }
    });
});


<!-- below is working fine -->

<script type="text/javascript" >
/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    // sample static data for the store
    var myData = "";


Ext.Ajax.request({
   url: 'vendorservice?getdata=true',
   success: function(response, opts) {
      myData = Ext.decode(response.responseText);
   },
   failure: function(response, opts) {
      console.log('server-side failure with status code ' + response.status);
   }
});

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    function change(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    function pctChange(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '%</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    }

    // create the data store
var store = new Ext.data.JsonStore({
    // store configs
    autoDestroy: true,
    autoLoad: true,
    url: '<%=getServletContext().getContextPath()%>/vendorservice?getdata=true',
    // reader configs
    idProperty: 'tvendorservicePk',
    fields: ['tvendorservicePk', 'weightfrom', 'weightto','rate', {name:'validto', type:'date'}]
});

    // manually load local data
    //store.loadData(myData);

    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {id:'tvendorservicePk',header: 'id', width: 160, sortable: true, dataIndex: 'tvendorservicePk'},
            {header: 'rate', width: 75, sortable: true,  dataIndex: 'rate'},
            {header: 'weightfrom', width: 75, sortable: true,  dataIndex: 'weightfrom'},
            {header: 'valid to', width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('YYYY-mm-dd'), dataIndex: 'validto'}
        ],
        stripeRows: true,
        autoExpandColumn: 'tvendorservicePk',
        height: 350,
        width: 600,
        title: 'Array Grid',
        // config options for stateful behavior
        stateful: true,
        stateId: 'grid'
    });

    // render the grid to the specified div in the page
    grid.render('grid-example');
});

</script>