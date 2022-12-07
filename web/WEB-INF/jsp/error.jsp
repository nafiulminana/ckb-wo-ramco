

<%@ page isErrorPage="true" import="java.io.*" %>

<html>

<head>

       <title>An unexpected exception has occurred!</title>

       <style>

       body, p { font-family:Tahoma; font-size:10pt; padding-left:30; }

       pre { font-size:8pt; }

       </style>

</head>

<body>



<%-- Exception Handler --%>

An unexpected exception has occured. Please contact Administrator. Message :
<font color="red">

    <br>
<%= exception.toString() %>

</font>



<%

out.println("<!--");

StringWriter sw = new StringWriter();

PrintWriter pw = new PrintWriter(sw);

exception.printStackTrace(pw);

out.print(sw);

sw.close();

pw.close();

out.println("-->");

%>



</body>

</html>

