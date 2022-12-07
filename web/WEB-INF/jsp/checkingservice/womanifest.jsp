<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib  prefix="sfx" uri="http://shido.com" %>
<!DOCTYPE html>
<sfx:view template="../template/maintemplate.jsp">
    <sfx:insert name="jsArea">
        <script>
            var arData = new Array();
            var dataTemp = $('#tabledetailid').memory();
//            var data = '{"total":6, page:1, numrow: 10, "rows":[{"name":"Create Work Order","meta_code":"CRW","menu_pk":"1"},{"name":"Create Inquiry","meta_code":"CRI","menu_pk":"2"},{"name":"Create Vendor Work Order","meta_code":"CRV","menu_pk":"3"},{"name":"Review Work Order","meta_code":"RWO","menu_pk":"4"},{"name":"Manage Users","meta_code":"MGU","menu_pk":"5"},{"name":"Print Work Order","meta_code":"PRW","menu_pk":"6"}]}';
//            dataTemp.memory({
//                data:data
//            });

            $(document).ready(function() {
                $("#purchase-form").simpleform();
                $('#tabledetailid').memory({
                    url: '${baseURL}/app/womanifest.jsp/list'
                });

//                $('#tablehistoryid').memory({
//                    url: '${baseURL}/app/order.jsp/listHistory?type=${requestScope.order.typeNotif}'
//                });
                $('#tabledetailid').simpletable({
                    data: $('#tabledetailid').memory("loadFromUrl"),
                    caption: 'WO List',
                    width: '100%',
                    height: '100%',
                    fieldmaxheight: '380px',
                    pager: true,
                    colModel: [
//                        {name: "Vendor Status", field: 'in_approval', width: 50, style: "text-align:center;", format: function(cellValue, rowIndex, colPos) {
//                                if (cellValue == 'Y') {
//                                    return '<img src="${pageContext.servletContext.contextPath}/includes/images/Ok-icon.png" alt="On Approve"/>';
//                                } else if (cellValue == 'N') {
//                                    return '<img src="${pageContext.servletContext.contextPath}/includes/images/edit-icon.png" alt="Vendor WO Not Build"/>';
//                                } 
//                            }},
                        {name: "Action", field: 'wo_pk', width: 100, style: "text-align:center;", format: function(cellValue, rowIndex, colPos) {
                                /* if (cellValue == 'approved') {
                                 return '<img src="${pageContext.servletContext.contextPath}/includes/images/Ok-icon.png"/>';
                                 } else if (cellValue == 'cancel') {
                                 return '<img src="${pageContext.servletContext.contextPath}/includes/images/delete-icon.png"/>';
                                 } else if (cellValue == 'ongoing') {
                                 return '<img src="${pageContext.servletContext.contextPath}/includes/images/attention-icon.png"/>';
                                 }*/
                                var ar = cellValue.split(',');
                                return '<a href="" onclick="window.open(\'http://10.144.250.8/WorkOrderManagementLive/list/print.do?wo=' + ar[0] + '&t=' + ar[1] + '\', \'newwindow\', \'width=900,height=1500\'); return false;">View Report</a>';
                            }},
//                        {name: "Action.", field: 'wo_pk', hidden: true, style: "text-align:center;", width: 100},
//                        {name: "Action.", field: 'wo_pk', hidden: true, style: "text-align:center;", width: 100},
                        {name: "WO#", field: 'wo_no', hidden: false, style: "text-align:center;", width: 200},
                        {name: "DA#", field: 'da', hidden: false, style: "text-align:center;", width: 100},
                        {name: "Manifest#", field: 'manifest_no', hidden: false, style: "text-align:center;", width: 100},
                        {name: "WO Status.", field: 'wostatus', hidden: false, style: "text-align:center;", width: 100}
//                        {name: "Origin.", field: 'origin', hidden: false, style: "text-align:center;", width: 500},
//                        {name: "Destination.", field: 'dest', hidden: false, style: "text-align:center;", width: 500}

                    ]
                    /* buttonModel: [
                     {
                     id: 'approveid',
                     name: "Approve",
                     action: function() {
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'ongoing') {
                     
                     $("#dialog-detail-wo-approve").dialog("open");
                     
                     
                     } else {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'approved') {
                     alert('WO already Approved!');
                     } else {
                     alert('WO already Canceled!');
                     }
                     }
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     }
                     },
                     {
                     id: 'cancelid',
                     name: "Cancel",
                     action: function() {
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'ongoing') {
                     var r = confirm('Are You want to Cancel?');
                     if (r == true)
                     {
                     $.ajax({
                     type: 'POST',
                     url: '${baseURL}/app/order.jsp/cancel',
                     data: {wo_pk: $('#tabledetailid').simpletable('getSelectedData').wo_pk},
                     async: false,
                     });
                     $('#tabledetailid').memory('setUrl', '${baseURL}/app/order.jsp/list');
                     $('#tabledetailid').simpletable('setData', $('#tabledetailid').memory('loadFromUrl'));
                     $('#tabledetailid').simpletable('refresh');
                     }
                     } else {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'approved') {
                     alert('WO already Approved!');
                     } else {
                     alert('WO already Canceled!');
                     }
                     }
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     }
                     }, '-',
                     {
                     id: 'newid',
                     name: "New",
                     action: function() {
                     document.location = '${baseURL}/app/order.jsp/create';
                     //                                console.log($('#tabledetailid').simpletable('getSelectedData').name);
                     }
                     },
                     {
                     id: 'editid',
                     name: "Edit",
                     action: function() {
                     if ($("#tabledetailid").simpletable('getSelectedData').wo_pk != undefined) {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'ongoing') {
                     window.location = '${baseURL}/app/order.jsp/setVariable/true?wo_pk=' + $('#tabledetailid').simpletable('getSelectedData').wo_pk
                     } else {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp != 'approved') {
                     alert('WO already Canceled!');
                     } else {
                     window.location = '${baseURL}/app/order.jsp/setVariable/true?wo_pk=' + $('#tabledetailid').simpletable('getSelectedData').wo_pk
                     }
                     }
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     //                                console.log($('#tabledetailid').simpletable('getSelectedData').name);
                     }
                     }, '-',
                     {
                     id: 'detailid',
                     name: "View Detail",
                     action: function() {
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     $('#orderbp').attr('src', '${baseURL}/app/order.jsp/doReport/html/master?wo_fk=' + $('#tabledetailid').simpletable('getSelectedData').wo_pk);
                     $('#dialog-detail').dialog('open');
                     $('#searchPositionId').val('master');
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     }
                     },
                     {
                     id: 'historyid',
                     name: "View History",
                     action: function() {
                     
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     $('#searchPositionId').val('history');
                     $('#tablehistoryid').memory('setUrl', '${baseURL}/app/order.jsp/listHistory?nowo=' + $('#tabledetailid').simpletable('getSelectedData').wo_no);
                     $('#tablehistoryid').simpletable('setData', $('#tablehistoryid').memory('loadFromUrl'));
                     $('#tablehistoryid').simpletable('refresh');
                     $('#history-detail').dialog('open');
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     }
                     },
                     '-', {
                     id: 'woreceiveddateid',
                     name: "WO Received",
                     action: function() {
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'approved') {
                     //                                        $('#woapprovalvalid').val($('#tabledetailid').simpletable('getSelectedData').sap_id);
                     $("#dialog-wo-received").dialog('open');
                     //                                        if ($('#tabledetailid').simpletable('getSelectedData').finance_ts == 'wat') {
                     //                                            var r = confirm('Are You want to Approve?');
                     //                                            if (r == true)
                     //                                            {
                     //                                                $.ajax({
                     //                                                    type: 'POST',
                     //                                                    url: '${baseURL}/app/sap.jsp/approve',
                     //                                                    data: {sap_id: $('#tabledetailid').simpletable('getSelectedData').sap_id, userid: '${personel.userId}'},
                     //                                                    async: false,
                     //                                                });
                     //                                                $('#tabledetailid').memory('setUrl', '${baseURL}/app/sap.jsp/listSap');
                     //                                                $('#tabledetailid').simpletable('setData', $('#tabledetailid').memory('loadFromUrl'));
                     //                                                $('#tabledetailid').simpletable('refresh');
                     //                                            }
                     //                                        } else {
                     //                                            if ($('#tabledetailid').simpletable('getSelectedData').in_approval == 'APV') {
                     //                                                alert('SAP already Submit To Finance!');
                     //                                            } else {
                     //                                                alert('SAP already Canceled!');
                     //                                            }
                     //                                        }
                     } else {
                     alert('WO must Approved first!');
                     }
                     } else {
                     alert('Please Choose WO First!');
                     }
                     }
                     },
                     {
                     id: 'approvepoid',
                     name: "PO Released",
                     action: function() {
                     
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'approved') {
                     $("#dialog-detail-po").dialog('open');
                     
                     
                     } else {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'ongoing') {
                     alert('WO need to Approve');
                     }
                     }
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     }
                     }, {
                     id: 'detailPO',
                     name: "Detail PO",
                     action: function() {
                     
                     if ($('#tabledetailid').simpletable('getSelectedData').wo_pk != undefined) {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'approved') {
                     $("#po-detail").dialog('open');
                     $('#tablepoid').memory({
                     url: '${baseURL}/app/order.jsp/listPO'
                     });
                     $('#tablepoid').simpletable({
                     data: $('#tablepoid').memory("loadFromUrl"),
                     width: '100%',
                     height: '100%',
                     pager: false,
                     colModel: [
                     {name: "Purchase Order Number.", field: 'purchase_order', hidden: false, style: "text-align:center;", width: 100},
                     {name: "Purchase Order Date", field: 'po_date', hidden: false, style: "text-align:center;", width: 100}
                     ]
                     });
                     
                     $('#tablepoid').memory("setUrl", "${baseURL}/app/order.jsp/listPO?nowo=" + $('#tabledetailid').simpletable('getSelectedData').wo_pk);
                     $('#tablepoid').simpletable("setData", $('#tablepoid').memory("loadFromUrl"));
                     $('#tablepoid').simpletable("refresh");
                     } else {
                     if ($('#tabledetailid').simpletable('getSelectedData').approved_by_bp == 'ongoing') {
                     alert('WO need to Approve');
                     }
                     }
                     } else {
                     alert('Please Choose WO No. First!');
                     }
                     }
                     },
                     //                        {
                     //                            name: "Export To PDF",
                     //                            action: function() {
                     //                                window.open('${baseURL}/app/order.jsp/doReport?wo_fk=' + $('#tabledetailid').simpletable('getSelectedData').wo_no, '', 'width=100,height=100');
                     //
                     //                            }
                     //                        },
                     {
                     id: 'searchid',
                     name: "Search",
                     style: "float:right",
                     action: function() {
                     $('#dialog-modal').dialog('open');
                     $('#searchPositionId').val('master');
                     }
                     }
                     ]*/
                });
                var butid = 'viewid';
                $('#' + butid).click(function() {
                    console.log('test');
                });
                $('#tablehistoryid').simpletable({
                    data: $('#tablehistoryid').memory("loadFromUrl"),
                    width: '100%',
                    height: '100%',
                    pager: true,
                    colModel: [
                        {name: "Action.", field: 'wo_pk', hidden: true, style: "text-align:center;", width: 100},
                        {name: "WO#", field: 'wo_no', hidden: false, style: "text-align:center;", width: 100},
                        {name: "DA#", field: 'da', hidden: false, style: "text-align:center;", width: 100},
                        {name: "Manifest#", field: 'manifest_no', hidden: false, style: "text-align:center;", width: 100},
                        {name: "Origin.", field: 'origin', hidden: false, style: "text-align:center;", width: 100},
                        {name: "Destination.", field: 'dest', hidden: false, style: "text-align:center;", width: 100},
                        {name: "WO Status.", field: 'wostatus', hidden: false, style: "text-align:center;", width: 100}

                    ],
                    buttonModel: [
                        {
                            id: 'detailhisid',
                            name: "View Detail",
                            action: function() {
                                if ($('#tablehistoryid').simpletable('getSelectedData').wo_pk !== undefined) {
                                    $('#searchPositionId').val('history');
                                    $('#orderbp').attr('src', '${baseURL}/app/order.jsp/doReport/html/history?wo_fk=' + $('#tablehistoryid').simpletable('getSelectedData').wo_pk);
                                    $('#dialog-detail').dialog('open');

                                } else {
                                    alert('Please Choose WO No. First!');
                                }
                            }
                        }
//                        {
//                            name: "Export To PDF",
//                            action: function() {
//                                window.open('${baseURL}/app/order.jsp/doReport?wo_fk=' + $('#tabledetailid').simpletable('getSelectedData').wo_no, '', 'width=100,height=100');
//
//                            }
//                        },

                    ]
                });

                $("#dialog-modal").dialog({
                    width: 500,
                    height: 200,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $("#dialog-detail").dialog({
                    width: 940,
                    height: 550,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $("#dialog-detail-po").dialog({
                    width: 540,
                    height: 250,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $("#dialog-detail-wo-approve").dialog({
                    width: 540,
                    height: 250,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $("#history-detail").dialog({
                    width: 900,
                    height: 480,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $("#po-detail").dialog({
                    width: 480,
                    height: 380,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $("#dialog-wo-received").dialog({
                    width: 500,
                    height: 200,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
                $(".date").datepicker({
                    dateFormat: "dd-mm-yy",
                    altFormat: "yy-mm-dd",
                    defaultDate: new Date()
                });
                $(".date").datepicker("setDate", new Date());
                $("#dialog-detail-wo-check").dialog({
                    width: 540,
                    height: 250,
                    modal: true,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });
            });
            function find() {
                var nomorId = $('#NameWoId').val().replace(/\r\n|\r|\n/g, ",");
                $('#tabledetailid').memory("setUrl", "${baseURL}/app/womanifest.jsp/list?nowo=" + nomorId + '&searchtype=' + $('#SearchTypeId').val());
                $('#tabledetailid').simpletable("setData", $('#tabledetailid').memory("loadFromUrl"));
                $('#tabledetailid').simpletable("refresh");
            }
            function iprint(ptarget)
            {
                ptarget.focus();
                ptarget.print();
            }
            function exportPDF() {
                if ($('#searchPositionId').val() === 'master') {
                    window.open('${baseURL}/app/order.jsp/doReport/pdf/master?wo_fk=' + $('#tabledetailid').simpletable('getSelectedData').wo_pk + '&userid=${personel.userId}', '', 'width=100,height=100');
                } else {
                    window.open('${baseURL}/app/order.jsp/doReport/pdf/history?wo_fk=' + $('#tabledetailid').simpletable('getSelectedData').wo_pk, '', 'width=100,height=100');
                }
            }
            function submitform() {
                if ($("#purchase-form").simpleform("validate")) {
                    $.ajax({
                        type: 'POST',
                        url: '${baseURL}/app/order.jsp/savepo',
                        data: {wono: $('#tabledetailid').simpletable('getSelectedData').wo_pk, ponumber: $('#ponumberid').val(), datepo: $('#datepoid').val(), userid: '${personel.userId}'},
                        async: false,
                        success: function(data) {
                            if (data === 'N') {
                                alert("Please insert booking number first!.");
                            }
                        }
                    });
                    $('#tabledetailid').memory('setUrl', '${baseURL}/app/order.jsp/list');
                    $('#tabledetailid').simpletable('setData', $('#tabledetailid').memory('loadFromUrl'));
                    $('#tabledetailid').simpletable('refresh');
                    $("#dialog-detail-po").dialog('close');
                }
            }
            function submitformwo() {
                if ($('#datewoid').val() !== '') {
                    $.ajax({
                        type: 'POST',
                        url: '${baseURL}/app/order.jsp/approve',
                        data: {wo_pk: $('#tabledetailid').simpletable('getSelectedData').wo_pk, datewo: $('#datewoid').val()},
                        async: false
                    });
                    $('#tabledetailid').memory('setUrl', '${baseURL}/app/order.jsp/list');
                    $('#tabledetailid').simpletable('setData', $('#tabledetailid').memory('loadFromUrl'));
                    $('#tabledetailid').simpletable('refresh');

                    $("#dialog-detail-wo-approve").dialog('close');
                } else {
                    alert("Date Approval Required!");
                }
            }
            function submitwo() {
                if ($('#woreceiveddatesid').val() !== '') {
                    $.ajax({
                        type: 'POST',
                        url: '${baseURL}/app/order.jsp/submitworeceived',
                        data: {wo_id: $('#tabledetailid').simpletable('getSelectedData').wo_pk, datewo: $('#woreceiveddatesid').val(), userid: '${personel.userId}'},
                        async: false
                    });
                    $('#tabledetailid').memory('setUrl', '${baseURL}/app/order.jsp/list');
                    $('#tabledetailid').simpletable('setData', $('#tabledetailid').memory('loadFromUrl'));
                    $('#tabledetailid').simpletable('refresh');

                    $("#dialog-wo-received").dialog('close');
                } else {
                    alert("Date Received Required!");
                }
            }
            function openSearchWo() {
                $("#dialog-detail-wo-check").dialog("open");
            }
            function closepo() {
                $("#dialog-detail-po").dialog('close');
            }
            function closewo() {
                $("#dialog-detail-wo-approve").dialog('close');
            }
            function exportXls() {
                //window.open('${baseURL}/app/reportwo.jsp/doReport/loads?'+ '&datefrom=' + $('#datefromid').val() + '&dateto=' + $('#datetoid').val(), '', 'width=100,height=100');
                window.open('${baseURL}/app/report.jsp/doReportWo/pdf/master?type='+$('#shiptypeid').val()+'&datefrom=' + $('#datefromid').val()+'&dateto='+$('#datetoid').val(), '', 'width=100,height=100');
            }
        </script>
        <style>
            ui-dialog-title{
                background-color: #A1C81E;
            }
        </style>
    </sfx:insert>
    <sfx:insert name="centerContent">
        <jsp:include page="../inc/notification.jsp"/>
        <table border="0">
            <tr>
                <td>
                    Search Type. :
                </td>
                <td>
                    <!--<input type="text" name="NameWo" id="NameWoId" value="" class="simpleui"/>-->
                    <select  name="SearchType" id="SearchTypeId">
                        <option value="MAN">Manifest</option>
                        <option value="DA">DA/MPS</option>
                        <option value="WO">WO</option>
                    </select>

                </td>
            </tr>
            <tr>
                <td>
                    Reference No. :
                </td>
                <td>
                    <!--<input type="text" name="NameWo" id="NameWoId" value="" class="simpleui"/>-->
                    <textarea rows="4" cols="50" name="NameWo" id="NameWoId" class="simpleui"></textarea>
                </td>
            </tr>

            <tr style="height: 5px;">
                <td>

                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <button onclick="find()">Search</button> &nbsp;&nbsp;&nbsp;<button onclick="openSearchWo()">Report</button>
                </td>
                <td>

                </td>
                <td>

                </td>
                <td style="text-align: right;">


                </td>
            </tr>

        </table>
        <table id="tabledetailid" ></table>


        <div id="dialog-detail-wo-check" title="Wo Checking">

            <table border="0" style="width: 100%;  margin-bottom: 0px; padding-top: 20px;">

                <tr>
                    <td>
                        Date Report:
                    </td>
                    <td>
                        From :<input type="text" name="datefrom" id="datefromid" class="simpleui date" />
                        To :<input type="text" name="dateto" id="datetoid" class="simpleui date" />
                    </td>
                </tr>
            </table>

            <table border="0" style="width: 95%;  bottom: 0px; padding-right: 0px; position: absolute;">
                <tr style="text-align: right">
                    <td>
                        <button value="btn" onclick="exportXls(); return false;">Search</button>&nbsp;<button value="btn" onclick="closewo();
                return false">Close</button>
                    </td>
                    <td>

                    </td>
                </tr>
            </table>

        </div>
    </sfx:insert>
</sfx:view>
