<%-- 
    Document   : index.jsp
    Created on : Feb 28, 2013, 10:59:29 AM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sfx" uri="http://shido.com"%>
<!DOCTYPE html>
<sfx:view template="basetemplate.jsp">
    <sfx:insert name="javascriptArea">
        
        <sfx:define name="jsArea"/>
        <script>
            $(document).ready(function(){
                $("#quickSearch").simpleform();
            });
        </script>
    </sfx:insert>
    <sfx:insert name="body">
        <table style="width:100%;height: 100%;">
            <tr>
                <td colspan="2" style="height: 3px;">
<!--                    <h3>Welcome To Work Order Management System</h3><br/>-->
                </td>
            </tr>
            <tr>
                <!--<td style="width:16%;vertical-align: top;">-->
<!--                    <table style="width: 100%;border-collapse: collapse;">
                        <tr>
                            <td style="border-bottom: #999 thin solid;width:1%;height: 1em;">
                                &nbsp;
                            </td>
                            <td style="border: #999 thin solid; padding: 5px;background-color: #A1C81E;text-shadow: white 0.2em 0.1em 0.2em;">
                                <b>Notifications</b>
                            </td>
                            <td style="border-bottom: #999 thin solid">
                                &nbsp;
                            </td>
                        </tr>
                        <tr style="background-color: #FFF;">
                            <td colspan="3" style="width:100%;height: 100%; margin: 0px; padding: 0px;">
                                <div id='notifications' style="width: 100%;height: 100%;">
                                    <jsp:include page="/sfx/app/leftnotification.jsp"/>
                                </div>
                            </td>
                        </tr>-->
<!--                        <tr>
                            <td style="border-bottom: #999 thin solid;width:1%;height: 1em;">
                                &nbsp;
                            </td>
                            <td style="border: #999 thin solid;padding: 5px; white-space: nowrap;">
                                Quick Search
                            </td>
                            <td style="border-bottom: #999 thin solid">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <form method="post" action="${baseURL}/main.jsp/search" name="quickSearch" id="quickSearch">
                                    <table style="width:100%;padding:5px;">
                                        <tr>
                                            <td>Look in:</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <select class="simpleui" name="lookupText" id="lookupType" style="width: 100%">
                                                    <option value="1">Inquiry</option>
                                                    <option value="2">Main Order</option>
                                                    <option value="3">Vendor Order</option>
                                                    <option value="4">Smart Search</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Search:
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input class="simpleui" style="width:100%; border: #999" type="text" name="lookupText" id="lookupText"/>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>-->
                    <!--</table>-->
                <!--</td>-->
                <td style="width: 100%; height: 100%;border: #999 thin solid; padding: 0px; margin: 0px; vertical-align: top; background-color: #FFF; ">
                    <sfx:define name="centerContent"/>
                </td>
            </tr>
        </table>
    </sfx:insert>
</sfx:view>