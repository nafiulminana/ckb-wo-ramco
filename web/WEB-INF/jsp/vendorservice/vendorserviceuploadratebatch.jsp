<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%-- 
    Document   : vendorserviceuploadrate
    Created on : Aug 13, 2010, 9:26:22 AM
    Author     : Admin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/scripts/displaytag/displaytag.css" />
<style>
    table {
	border: 1px solid #666;
	width: 100%;
	margin: 20px 0 20px 0 !important;
    }
    
    .not-valid{
        background-color: red !important;
        color : #fff !important;
    }
</style>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<html>
    <head>
        <title>Upload rate vendor</title>
    </head>
    <body style="padding : 15px;">
        <strong>Upload your rate Batch file (excel 2003 format, max 5MB)</strong>
        <form method="post" id="VendorServiceFileUploadRateServletform" action="VendorServiceFileUploadRateServlet" enctype="multipart/form-data">
            <label for="file">Upload</label>
            <input id="file" type="file" name="myfile">
            <input type="button" value="Upload" id="submitbutton" />
            <input type="button" value="Back" id="back" onclick="document.location.href='<%=application.getContextPath()%>/vendorservice'" />
        </form>
        <br/>
        <a href="VendorServiceFileUploadRateServlet?template=true"><input type="button" value="Download Template" /></a>
        <br/>
        <c:if test="${sessionScope['message'] != null}">
            <div>${message}</div>
        </c:if>
        <table>
            <thead>
                <th class="sortable">No</th>
                <th class="sortable">Vendor</th>
                <th class="sortable">Currency</th>
                <th class="sortable">Rate</th>
                <th class="sortable">Service Name</th>
                <th class="sortable">Service Mode</th>
                <th class="sortable">Service Mode Detail</th>
                <th class="sortable">Remarks</th>
                <th class="sortable">Order</th>
                <th class="sortable">Delivery Term</th>
                <th class="sortable">Origin</th>
                <th class="sortable">Destination</th>
                <th class="sortable">Valid From</th>
                <th class="sortable">Valid To</th>
                <th class="sortable">Weight From</th>
                <th class="sortable">Weight To</th>
                <th class="sortable">Minimum Weight</th>
                <th class="sortable">Refagreement No</th>
                <th class="sortable">Refagreement Date</th>
                <th class="sortable">Is Rate Flat</th>
            </thead>
                <c:if test="${sessionScope['uploadVendorService'] != null}">
                    <c:forEach items="${sessionScope['uploadVendorService']}" var="vendorService" varStatus="loop">
                        <tr class="<c:if test="${vendorService.isValidData != null && vendorService.isValidData == false}">not-valid</c:if>">
                            <td>${loop.index + 1}</td>
                            <td>${vendorService.vendor.vendorname}</td>
                            <td>${vendorService.currency.currencycode}</td>
                            <td>${vendorService.rate}</td>
                            <td>${vendorService.serviceType.servicename}</td>
                            <td>${vendorService.serviceMode.smodename}</td>
                            <td>${vendorService.serviceModeDetail.smdname}</td>
                            <td>${vendorService.remarks}</td>
                            <td>${vendorService.order.ordername}</td>
                            <td>${vendorService.deliveryTerm.dtname}</td>
                            <td>${vendorService.originLocation.locationname}</td>
                            <td>${vendorService.destinationLocation.locationname}</td>
                            <td>${vendorService.validfrom}</td>
                            <td>${vendorService.validto}</td>
                            <td>${vendorService.weightfrom}</td>
                            <td>${vendorService.weightto}</td>
                            <td>${vendorService.minimumweight}</td>
                            <td>${vendorService.refagreementno}</td>
                            <td>${vendorService.refagreementdate}</td> 
                            <td>${vendorService.isFlatRate}</td> 
                        </tr> 
                    </c:forEach>
                </c:if>
            </tbody>
            <br/>
        </table>
        
        <p  id="wait" style="text-align: center;"><img id="progress_image" style="padding-left:5px;padding-top:5px;" src="<%=getServletContext().getContextPath()%>/images/ajax-loader.gif" alt=""> Uploading in progress…<p>
        <form method="post" id="VendorServiceFileUploadRateServletformSave" action="VendorServiceFileUploadRateServlet">
            <input type="hidden" name="SaveData" value="save">
            <input type="submit" value="Save" id="saveData" onclick="document.location.href='<%=application.getContextPath()%>/vendorservice'" />
        </form>
    </body>


<script type="text/javascript" >
$('#submitbutton').click(function() {
    $('#wait').show();
    $('#VendorServiceFileUploadRateServletform').submit();
    return false;
});

$('#saveData').click(function() {
    $('#wait').show();
    $('#VendorServiceFileUploadRateServletformSave').submit();
    return false;
});

$('#wait').hide();
<c:if test="${sessionScope['success'] != null}">
    Swal.fire({
        icon: 'success',
        title: 'Your data has been saved',
        showConfirmButton: true,
        html : "${sessionScope['success']}",
        timer: 3000
    })
</c:if>

</script>
</html>
