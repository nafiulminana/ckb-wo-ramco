<%-- 
    Document   : index
    Created on : Apr 29, 2010, 3:56:54 AM
    Author     : Amaran Sidhiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    com.ckb.wo.client.model.UserBeans ub = (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
    if (ub != null) {
        if (ub.isLogon()) {
            response.sendRedirect("main.jsp");
        }
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%= application.getInitParameter("appTitle")%></title>
        <link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/scripts/jquery-ui.css"/>
        <script type="text/javascript" src="<%=application.getContextPath()%>/scripts/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath()%>/scripts/jquery-ui.js"></script>
        <style type="text/css">
            @import url( login_files/admin_login.css );
            #Layer1 {
                position:absolute;
                left:22px;
                top:86px;
                width:702px;
                height:169px;
                z-index:1;
                overflow: scroll;
            }
            .style1 {color: #CC0000}
            .style2 {color: #FFFFFF}
            .style4 {
                color: #FF9900;
                font-weight: bold;
                font-size: 16px;
            }
            .style5 {color: #99FF99}
            .style15 {	font-family: Arial, Helvetica, sans-serif;
                       font-size: 10px;
            }
            .style16 {color: #FF0000}

            /* Tab Style */
            div.tab {
                overflow: hidden;
                border: 1px solid #fff;
                background-color: #f1f1f1;
            }

            /* Style the buttons inside the tab */
            div.tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
            }

            /* Change background color of buttons on hover */
            div.tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current tablink class */
            div.tab button.active {
                background-color: #ccc;
            }

            /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            } 

            .tabcontent {
                -webkit-animation: fadeEffect 1s;
                animation: fadeEffect 1s; /* Fading effect takes 1 second */
            }

            @-webkit-keyframes fadeEffect {
                from {opacity: 0;}
                to {opacity: 1;}
            }

            @keyframes fadeEffect {
                from {opacity: 0;}
                to {opacity: 1;}
            }


        </style>
        <script type="text/javascript">
            $(function () {
                $("#tabs").tabs();
            });
        </script>
    </head>
    <!--<body onload="openCity(this, 'loginId');">-->
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="mambo"><img alt="Logo"
                                     src="login_files/header_text.png" /></div>
            </div>
        </div>
        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">Login</a></li>
                <li><a href="#tabs-2">Planning</a></li>
                <li><a href="#tabs-3">WO DA/MPS Checking</a></li>
            </ul>
            <div id="tabs-1">

                <div class=login>
                    <div class=login-form><img alt=Login src="login_files/login.gif"/>
                        <form method="POST" id="loginForm" name="loginForm" class="KT_tngformerror" action="ValidateLogin">
                            <div class=form-block>
                                <%
                                    if (ub != null) {
                                        if (!ub.isLogon()) {
                                            System.out.println(ub.getErrMessage());
                                            if (request.getParameter("logout") != null) {
                                %>
                                <div style="background-color:yellow; color: black;border: orange dotted thin; padding: 5px 5px 5px 5px">
                                    You are Already Log out!
                                </div>
                                <%                        } else {
                                %>
                                <div style="color: red;border: red dotted thin; padding: 5px 5px 5px 5px">
                                    ${sessionScope["loginInfo"].errMessage}
                                </div>
                                <%                                    }
                                            session.invalidate();
                                        }
                                    }
                                %>
                                <div class=inputlabel>Username</div>
                                <div>
                                    <input type="text" name="txtUser" id="txtUser" value="" class=inputbox size=15 />
                                </div>
                                <div class=inputlabel>Password</div>
                                <div>
                                    <input class=inputbox type="password" name="txtPassword" id="txtPassword" value="" size="15" />
                                </div>
                                <div class=inputlabel>Remember me:
                                    <input type="checkbox" name="chkRemember" id="chkRemember" value="1" />
                                </div>
                                <div align=left>
                                    <input type="submit" name="btnLogin" id="btnLogin" value="Login" />
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class=login-text>
                        <div class=ctr><img height=64 alt=security src="login_files/security.png"
                                            width=64/></div>
                        <P>Welcome to Work Order Management</P>
                        <P>Use a valid username and password to gain access to the administration
                            console.</P>
                    </div>
                    <div class=clr></div>
                </div>
            </div>
            <div id="tabs-2">
                <iframe src="<%= application.getContextPath()%>/vendorservice" style="height: 500px; width: 100%; border: 0; overflow-y: auto;" ></iframe>
            </div>
            <div id="tabs-3">
                <iframe src="<%= application.getContextPath()%>/checkingservice" style="height: 600px; width: 100%; border: 0; overflow-y: auto;" ></iframe>
            </div>
        </div>
        <div align="center">
            <style type="text/css">
                .style14 {
                    font-size: 10px;
                    color: #0000FF;
                }
                .style15 {
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 10px;
                }
                .style16 {color: #FF0000}
            </style>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>