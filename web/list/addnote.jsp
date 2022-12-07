<%-- 
    Document   : addnote.jsp
    Created on : Jul 6, 2011, 4:57:43 PM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.ckb.wo.server.service.util.*,com.ckb.wo.client.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
                    com.ckb.wo.client.model.UserBeans ub =
                            (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
                    if (ub != null) {
                        if (!ub.isLogon()) {
                            out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                        }
                    } else {
                        out.print("<script type='text/javascript'>parent.location.href='" + application.getContextPath() + "/index.jsp'</script>");
                    }

    if(request.getParameter("btnSave")!=null){
        Notes note = new Notes();
        note.setTworkorderFk(new Long(request.getParameter("wo")));
        note.setEmployeeId(ub.getEmployeeId());
        note.setNoteType("NOTES");
        note.setSubject(request.getParameter("subject"));
        note.setNotes(request.getParameter("notes"));
        NotesLocalServiceUtil.saveNotes(note);

        response.sendRedirect("workorder_notes.jsp?wo="+note.getTworkorderFk());
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <jsp:include page="../scripts/inc_tiny.jsp"/>
    </head>
    <body>
        <h2>Add Notes</h2>
        <form action="addnote.jsp?wo=${param['wo']}" method="post">
            Subject:<br/>
            <input type="text" name="subject" value="" size="95"/><br/><br/>
            <textarea name="notes"></textarea>
            <input type="submit" value="Submit" name="btnSave"/> <a href="workorder_notes.jsp?wo=${param['wo']}">Back</a>
        </form>
    </body>
</html>
