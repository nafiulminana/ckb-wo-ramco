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
                    out.print("<script type='text/javascript'>parent.location.href='"+application.getContextPath()+"/index.jsp'</script>");
                }
            } else {
                    out.print("<script type='text/javascript'>parent.location.href='"+application.getContextPath()+"/index.jsp'</script>");
            }
        %>
        <script type="text/javascript">
            function select(manifestNo){
                parent.document.getElementById('manifest').value=manifestNo;
                parent.SeyLightbox.close();
            }
        </script>
    </head>
    <body>
        <h2>Manifest To Lookup: ${param['manifest']}</h2>
        <table align="center" border="1" style="border-collapse: collapse">
            <tr>
                <td>
                    <form id="searchfrm" name="searchfrm" method="post" action="manifestLookup.jsp">
                        <table>
                            <tr style="vertical-align: middle;">
                                <td>
                                    <input type="text" name="manifest" value="${param['manifest']}"/>
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
                                    <th>Manifest#</th>
                                    <th>Origin</th>
                                    <th>Destination</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                            String search = request.getParameter("manifest");
                                            //com.ckb.wo.client.model.MdaManifestExample example = new com.ckb.wo.client.model.MdaManifestExample();
                                            //if (!search.isEmpty()) {
                                                //example.createCriteria().andManifestNoEqualTo("%" + search.replaceAll("\'", "\'\'") + "%");
                                                //example.createCriteria().andManifestNoEqualTo(new Long(search));
                                            //}
                                            

                                            //int totalVendor = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.countManifestByExample(example);
                                            int totalManifest = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.countManifestByManifestNoWithLike("%"+search+"%");

                                            int totPage = Math.round(totalManifest / 10);
                                            int currPage = 0;
                                            try {
                                                currPage = new Integer(request.getParameter("page"));
                                                currPage = --currPage < 0 ? 0 : currPage;
                                                currPage = currPage > totPage ? totPage : currPage;
                                            } catch (Exception e) {
                                            }

                                            //example.setLimitClause((currPage * 10) + "," + 10);

                                            //java.util.List<com.ckb.wo.client.model.MdaManifest> lManifest = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getManifestByExample(example);
                                            java.util.List<com.ckb.wo.client.model.MdaManifest> lManifest = com.ckb.wo.server.service.util.ManifestDALocalServiceUtil.getManifestByManifestNoWithLike("%"+search+"%", (currPage * 10) + "," + 10);
                                            request.setAttribute("manifests", lManifest);
                                %>
                                <c:forEach var="manifest" items="${requestScope['manifests']}">
                                <tr>
                                    <td nowrap><a href="#" title="${manifest.alreadyUsedInWO?'Already Used':'Select'}" <c:if test="${!manifest.alreadyUsedInWO}">onclick="select('${manifest.manifestNo}')"</c:if>><img src="<%= getServletContext().getContextPath()%>/images/icons/${manifest.alreadyUsedInWO?'block_16.png':'tick_16.png'}" alt="select" border="0"/></a></td>
                                    <td nowrap>${manifest.manifestNo}</td>
                                    <td nowrap>${manifest.origin}</td>
                                    <td nowrap>${manifest.destination}</td>
                                    <td nowrap>${manifest.manifestStatus}</td>
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
                                <% if ((currPage + 1) > 1) {%><a href="#" onclick="location.href='manifestLookup.jsp?page=1<%=search != null ? "&manifest=" + search : ""%>'">First</a> <a href="#" onclick="location.href='manifestLookup.jsp?page=<%=currPage%><%=search != null ? "&manifest=" + search : ""%>'">Prev</a><% }%> Page: <%=(currPage + 1)%> Of <%=totPage%> <% if ((currPage + 1) < totPage) {%><a href="#" onclick="location.href='manifestLookup.jsp?page=<%=currPage+2%><%=search != null ? "&manifest=" + search : ""%>'">Next</a> <a href="#" onclick="location.href='manifestLookup.jsp?page=<%=totPage%><%=search != null ? "&manifest=" + search : ""%>'">Last</a><% }%>
                            </td>
                            <td style="text-align: right">
                                Showing record <%=(currPage * 10) + " to " + ((currPage * 10) + 10)%> of <%= totalManifest%> Manifest<%=totalManifest > 1 ? "s" : ""%>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
