<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Order Management</title>
        <link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/styles/bootstrap.min.css"/>
        <script type="text/javascript" src="<%=application.getContextPath()%>/scripts/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath()%>/scripts/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div>
            <form method="post" action="checkingservice" >
                <div class="form-group row">
                    <label for="searchType" class="col-sm-1 col-form-label-sm">Search Type</label>
                    <div class="col-sm-4">
                        <select id="searchType" name="searchType" class="form-control form-control-sm">
                            <option value="MAN" selected>Manifest</option>
                            <option value="DA">DA/MPS</option>
                            <option value="WO">WO</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="reffNo" class="col-sm-1 col-form-label-sm">Ref. No</label>
                    <div class="col-sm-4">
                        <textarea id="reffNo" name="reffNo" class="form-control form-control-sm"></textarea>
                    </div>
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-primary" style="margin-top: 30px">Search</button>
                    </div>
                </div>
            </form>
            <c:if test="${!empty woDaManStatuses}">
                <table class="table table-hover table-sm">
                    <thead>
                        <tr>
                            <th scope="col-1" style="text-align: center">Action</th>
                            <th scope="col-2" style="text-align: center">Work Order</th>
                            <th scope="col-2" style="text-align: center">DA</th>
                            <th scope="col-2" style="text-align: center">Manifest</th>
                            <th scope="col-2" style="text-align: center">Work Order Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${woDaManStatuses}" var="woDaManStatus">
                            <tr>
                                <td scope="col-1" style="text-align: center"><a href="" onclick="window.open('<%= application.getContextPath()%>/list/print.do?wo=<c:out value="${woDaManStatus.wopk}" />&amp;t=<c:out value="${t}" />', 'newwindow', 'width=900,height=1500'); return false;">View Report</a></td>
                                <td scope="col-2" style="text-align: center"><c:out value="${woDaManStatus.wo}" /></td>
                                <td scope="col-2" style="text-align: center"><c:out value="${woDaManStatus.da}" /></td>
                                <td scope="col-2" style="text-align: center"><c:out value="${woDaManStatus.man}" /></td>
                                <td scope="col-2" style="text-align: center"><c:out value="${woDaManStatus.status}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>            
        </div>
    </body>
</html>
