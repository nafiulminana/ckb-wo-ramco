<%-- 
    Document   : vendorserviceuploadrate
    Created on : Aug 13, 2010, 9:26:22 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/scripts/jquery-1.4.2.min.js"></script>


<html>
    <head>
        <title>Upload Rate Vendor</title>
    </head>
    <body>
        <strong>Upload your rate file (excel 2003 format, max 5MB)</strong>
        <form method="post" id="vendorservicefileuploadservletform" action="vendorservicefileuploadservlet" enctype="multipart/form-data">
            <label for="file">Upload</label>
            <input id="file" type="file" name="myfile">
            <input type="button" value="Upload" id="submitbutton" />
            <input type="button" value="Back" id="back" onclick="document.location.href='<%=application.getContextPath()%>/vendorservice'" />
        </form>

        <a href="vendorservicefileuploadservlet?logfile=true">download log</a>

        <p  id="wait"><img id="progress_image" style="padding-left:5px;padding-top:5px;" src="<%=getServletContext().getContextPath()%>/images/ajax-loader.gif" alt=""> Uploading in progress…<p>
    </body>


<script type="text/javascript" >
$('#submitbutton').click(function() {
    $('#wait').show();
    $('#vendorservicefileuploadservletform').submit();
    return false;
});

$('#wait').hide();
</script>
</html>
