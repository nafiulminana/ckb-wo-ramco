<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : manifestLookup.jsp
    Created on : Jul 6, 2010, 9:24:40 AM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manifest Lookup</title>
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
        %>
        <script type="text/javascript">
            function select(manifestNo){
                parent.document.getElementById('damps').value=manifestNo;
                parent.SeyLightbox.close();
            }
        </script>
    </head>
    <body>
        <h2>DA/MPS# To Lookup: ${param['manifest']}</h2>
        <table align="center" border="1" style="border-collapse: collapse">
            <tr>
                <td>
                    <form id="searchfrm" name="searchfrm" method="post" action="daLookup.jsp">
                        <table>
                            <tr style="vertical-align: middle;">
                                <td>
                                    DA <input type="radio" value="DA" name="daormps" ${param['daormps']=='DA'||param['daormps']==null?'checked':''}/> MPS <input name="daormps" type="radio" value="MPS" ${param['daormps']=='MPS'?'checked':''}/>
                                    <input type="text" name="da" value="${param['da']}"/>
                                    <input type="hidden" name="l" value="${param['l']}"/>
                                    <input type="hidden" name="v" value="${param['v']}"/>
                                </td>
                                <td><input type="image" name="search" value="search" src="<%= getServletContext().getContextPath()%>/images/icons/search_16.png"/></td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="manifestTable" style="overflow:scroll;width:500px; height: 350px">
                        <table cellpadding="3" border="1" style="border-collapse: collapse">
                            <thead>
                                <tr bgcolor="lightsteelblue">
                                    <th>Action</th>
                                    <th>DA/MPS#</th>
                                    <th>Origin</th>
                                    <th>Destination</th>
                                    <th>Total Package</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                            String search = request.getParameter("da");
                                            Long vendor = null;
                                            try {
                                                vendor = new Long(request.getParameter("v"));
                                            } catch (Exception e) {
                                                logger.LoggerClass.logDebug(e);
                                            }
                                            Long location = null;
                                            try {
                                                location = new Long(request.getParameter("l"));
                                            } catch (Exception e) {
                                                logger.LoggerClass.logDebug(e);
                                            }
                                            //com.ckb.wo.client.model.MdaManifestExample example = new com.ckb.wo.client.model.MdaManifestExample();
                                            //if (!search.isEmpty()) {
                                            //example.createCriteria().andManifestNoEqualTo("%" + search.replaceAll("\'", "\'\'") + "%");
                                            //example.createCriteria().andManifestNoEqualTo(new Long(search));
                                            //}


                                            //int totalVendor = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.countManifestByExample(example);
                                            boolean daOrMps = false;
                                            try {
                                                daOrMps = request.getParameter("daormps").equals("MPS") || !request.getParameter("daormps").equals("DA");
                                            } catch (Exception e) {
                                            }
                                            int totalDa = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.countShipmentByDANoWithLike("%" + search + "%",daOrMps);//"%"+search+"%");

                                            int totPage = Math.round(totalDa / 10);
                                            int currPage = 0;
                                            try {
                                                currPage = new Integer(request.getParameter("page"));
                                                currPage = --currPage < 0 ? 0 : currPage;
                                                currPage = currPage > totPage ? totPage : currPage;
                                            } catch (Exception e) {
                                            }

                                            //java.util.List<com.ckb.wo.client.model.MdaManifest> lManifest = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getManifestByManifestNoWithLike("%"+search+"%", (currPage * 10) + "," + 10);
                                            java.util.List<com.ckb.wo.client.model.Shipment> da = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getShipmentByDANoWithLike("%" + search + "%", (currPage * 10) + "," + 10, vendor, location, daOrMps);
                                            request.setAttribute("shipments", da);
                                %>
                                <c:forEach var="da" items="${requestScope['shipments']}">
                                    <tr>
                                        <td nowrap><%--<a href="#" title="select" onclick="select(${da.da})"><img src="<%= getServletContext().getContextPath()%>/images/icons/tick_16.png" alt="update" border="0"/></a>--%>
                                            <a href="#" title="${da.alreadyUsedInWO?'Already Used':'Select'}" <c:if test="${!da.alreadyUsedInWO}">onclick="select('${da.da}')"</c:if>><img src="<%= getServletContext().getContextPath()%>/images/icons/${da.alreadyUsedInWO?'block_16.png':'tick_16.png'}" alt="select" border="0"/></a>
                                        </td>
                                        <td nowrap>${da.da}</td>
                                        <td nowrap>${da.origin}</td>
                                        <td nowrap>${da.destination}</td>
                                        <td nowrap>${da.totPackage}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <table width="100%">
                        <tr>
                            <td>
                                <% if ((currPage + 1) > 1) {%><a href="#" onclick="location.href='daLookup.jsp?page=1<%=(search != null ? "&da=" + search : "") + "&v=" + vendor + "&l=" + location+ "&daormps=" + (daOrMps?"MPS":"DA")%>'">First</a> <a href="#" onclick="location.href='daLookup.jsp?page=<%=currPage%><%=(search != null ? "&da=" + search : "") + "&v=" + vendor + "&l=" + location+ "&daormps=" + (daOrMps?"MPS":"DA")%>'">Prev</a><% }%> Page: <%=(currPage + 1)%> Of <%=totPage%> <% if ((currPage + 1) < totPage) {%><a href="#" onclick="location.href='daLookup.jsp?page=<%=currPage + 2%><%=(search != null ? "&da=" + search : "") + "&v=" + vendor + "&l=" + location+ "&daormps=" + (daOrMps?"MPS":"DA")%>'">Next</a> <a href="#" onclick="location.href='daLookup.jsp?page=<%=totPage%><%=(search != null ? "&da=" + search : "") + "&v=" + vendor + "&l=" + location+ "&daormps=" + (daOrMps?"MPS":"DA")%>'">Last</a><% }%>
                            </td>
                            <td style="text-align: right">
                                Showing record <%=(currPage * 10) + " to " + ((currPage * 10) + 10)%> of <%= totalDa%> Da/MPS<%=totalDa > 1 ? "s" : ""%>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
