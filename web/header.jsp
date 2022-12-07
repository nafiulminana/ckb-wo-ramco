<div class="header_right">
    <div class="top_info">
        <div class="top_info_right">
            <p>
                <strong>Welcome </strong><span class="style1"><span class="merah">${sessionScope["loginInfo"].fullName}</span></span>
            - <a href="<%=getServletContext().getContextPath()%>/WO.pdf">Help</a> - <a href="<%=getServletContext().getContextPath()%>/Logout">Logout</a></p>
        </div>
    </div>
</div>
<div class="logo">
    <h1>
        <a href="<%=getServletContext().getContextPath()%>/main.jsp" title="Work Order Management">
            <img src="<%=getServletContext().getContextPath()%>/images/CKB Logo small size sm.jpg" width="113" height="44" border="0" />
            <h1 id="module"></h1>
    </a></h1>
</div>
<div class="subheader">
    <strong>Date: </strong>
    <span class="style3"><%=String.format("%1$tA, %1$te-%1$tB-%1$tY",new java.util.Date())%></span><br />
</div>