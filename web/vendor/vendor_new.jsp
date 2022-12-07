<%
            com.ckb.wo.client.model.UserBeans ub =
                    (com.ckb.wo.client.model.UserBeans) session.getAttribute("loginInfo");
            if (ub != null) {
                if (!ub.isLogon()) {
                    out.print("<script type='text/javascript'>parent.location.href='"+application.getContextPath()+"/index.jsp'</script>");
                }
                if(!com.ckb.wo.server.service.util.UserLocalServiceUtil.isProcurementUser(ub.getEmployeeId())){
                    out.print("<script type='text/javascript'>parent.location.href='"+application.getContextPath()+"/index.jsp'</script>");
                }
            } else {
                    out.print("<script type='text/javascript'>parent.location.href='"+application.getContextPath()+"/index.jsp'</script>");
            }
            boolean save = false;
            boolean error = false;
            boolean isnew = false;
            String errorMessage = "";
            if (request.getParameter("save") != null) {
                try {
                    com.ckb.wo.client.model.Vendor vendor = null;
                    if (session.getAttribute("vendor") == null) {
                        vendor = new com.ckb.wo.client.model.Vendor();
                        isnew=true;
                    } else {
                        vendor = (com.ckb.wo.client.model.Vendor) session.getAttribute("vendor");
                    }

                    vendor.setVendorcode(request.getParameter("vendorcode").replaceAll("'", "''"));
                    vendor.setVendorSAPID(request.getParameter("vendor_sap_id").replaceAll("'", "''"));
                    vendor.setVendorname(request.getParameter("vendorname").replaceAll("'", "''"));
                    vendor.setAddress1(request.getParameter("address1").replaceAll("'", "''"));
                    vendor.setAddress2(request.getParameter("address2").replaceAll("'", "''"));
                    vendor.setPostalcode(request.getParameter("postalcode").replaceAll("'", "''"));
                    vendor.setWebsite(request.getParameter("website"));
                    vendor.setEmail(request.getParameter("email"));
                    vendor.setVendorowner(request.getParameter("vendorowner").replaceAll("'", "''"));
                    try {
                        vendor.setPph(new java.math.BigDecimal(request.getParameter("pph")));
                    } catch (Exception e) {
                    }
                    try {
                        vendor.setPpn(new java.math.BigDecimal(request.getParameter("ppn")));
                    } catch (Exception e) {
                    }
                    vendor.setVendornpwp(request.getParameter("npwp"));
                    vendor.setContactname(request.getParameter("contactname").replaceAll("'", "''"));
                    vendor.setContactphone(request.getParameter("contactphone"));
                    vendor.setContactfax(request.getParameter("contactfax"));
                    vendor.setContactemail(request.getParameter("contactemail"));
                    vendor.setIsactive(true);
                    if (session.getAttribute("vendor") == null) {
                        com.ckb.wo.server.service.util.VendorLocalServiceUtil.insertVendor(vendor);
                    } else {
                        com.ckb.wo.server.service.util.VendorLocalServiceUtil.updateVendor(vendor);
                        session.removeAttribute("vendor");
                    }
                    save = true;
                } catch (Exception e) {
                    error = true;
                    errorMessage = e.toString();
                    e.printStackTrace();
                }
            } else {
                try {
                    com.ckb.wo.client.model.Vendor vendor = com.ckb.wo.server.service.util.VendorLocalServiceUtil.getVendorByPrimaryKey(new Long(request.getParameter("e")));
                    session.setAttribute("vendor", vendor);
                } catch (Exception e) {
                }
            }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>New Vendor</title>
    </head>
    <body>
        <form id="form1" name="form1" method="post" action="">
            <table border="0" align="center">
                <tr>
                    <td><strong>Vendor Information</strong></td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" border="0" style="border:solid black thin">
                            <tr>
                                <td nowrap><div align="right">Vendor#</div></td>
                                <td nowrap><input type="text" name="vendorcode" id="vendorcode" value="${sessionScope['vendor'].vendorcode}"/></td>
                            </tr>
                            <tr>
                                <td nowrap><div align="right">SAP#</div></td>
                                <td nowrap><input type="text" name="vendor_sap_id" id="vendor_sap_id" value="${sessionScope['vendor'].vendorSAPID}"/></td>
                            </tr>
                            <tr>
                                <td nowrap><div align="right">Vendor Name</div></td>
                                <td nowrap><input type="text" name="vendorname" id="vendorname" value="${sessionScope['vendor'].vendorname}"/></td>
                            </tr>
                            <tr>
                                <td rowspan="2" valign="top" nowrap><div align="right">Address </div></td>
                                <td nowrap><textarea name="address1" id="address1" cols="45" rows="3">${sessionScope['vendor'].address1}</textarea></td>
                            </tr>
                            <tr>
                                <td nowrap><textarea name="address2" id="address2" cols="45" rows="3">${sessionScope['vendor'].address2}</textarea></td>
                            </tr>
                            <tr>
                                <td nowrap><div align="right">Postal Code</div></td>
                                <td nowrap><input type="text" name="postalcode" id="postalcode" value="${sessionScope['vendor'].postalcode}"/></td>
                            </tr>
                            <tr>
                                <td nowrap><div align="right">Website</div></td>
                                <td nowrap><input type="text" name="website" id="website" value="${sessionScope['vendor'].website}"/></td>
                            </tr>
                            <tr>
                                <td nowrap><div align="right">Email</div></td>
                                <td nowrap><input type="text" name="email" id="email" value="${sessionScope['vendor'].email}"/></td>
                            </tr>
                            <tr>
                                <td nowrap><div align="right">Owner Country</div></td>
                                <td nowrap><input type="text" name="vendorowner" id="vendorowner"value="${sessionScope['vendor'].vendorowner}"/></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td><strong>Tax Information</strong></td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" style="border:solid black thin">
                            <tr>
                                <td nowrap ><div align="right">PPH</div></td>
                                <td nowrap ><input name="pph" type="text" id="pph" size="5" maxlength="3" value="${sessionScope['vendor'].pph}"/>%</td>
                                <td nowrap ><div align="right">PPN</div></td>
                                <td nowrap ><input name="ppn" type="text" id="ppn" size="5" maxlength="3" value="${sessionScope['vendor'].ppn}"/>%</td>
                            </tr>
                            <tr>
                                <td nowrap ><div align="right">TAXID/NPWP#</div></td>
                                <td colspan="3" nowrap ><input type="text" name="npwp" id="npwp" value="${sessionScope['vendor'].vendornpwp}"/></td>
                            </tr>
                            <tr>
                                <td nowrap valign="top"><div align="right">Remittance Address</div></td>
                                <td colspan="3" nowrap><textarea name="remittanceaddress" id="remittanceaddress" cols="45" rows="5">${sessionScope['vendor'].remittanceaddress}</textarea></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td><strong>Contact Information</strong></td>
                </tr>
                <tr>
                    <td><table width="100%" style="border:solid black thin">
                            <tr>
                                <td nowrap ><div align="right">Contact Name</div></td>
                                <td nowrap ><input type="text" name="contactname" id="contactname" value="${sessionScope['vendor'].contactname}"/></td>
                            </tr>
                            <tr>
                                <td nowrap ><div align="right">Contact Phone</div></td>
                                <td nowrap ><input type="text" name="contactphone" id="contactphone" value="${sessionScope['vendor'].contactphone}"/></td>
                            </tr>
                            <tr>
                                <td nowrap ><div align="right">Contact Fax</div></td>
                                <td nowrap ><input type="text" name="contactfax" id="contactfax" value="${sessionScope['vendor'].contactfax}"/></td>
                            </tr>
                            <tr>
                                <td nowrap ><div align="right">Contact Email</div></td>
                                <td nowrap ><input type="text" name="contactemail" id="contactemail" value="${sessionScope['vendor'].contactemail}"/></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td>
                        <table border="0" align="center">
                            <tr>
                                <td><input type="submit" name="save" id="Save" value="Save"/></td>
                                <td><input type="reset" name="reset" id="reset" value="Reset All Fields"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <p>&nbsp;</p>
        </form>
        <script type="text/javascript">
            <%
                        if (!save && error) {
                            out.println("alert('" + errorMessage + "');");
                        } else if (save) {
                            out.println("alert('Save Success...!');");
                            if(isnew){
                                out.println("parent.doSearch('" + request.getParameter("vendorname") + "');");
                            }else{
                                out.println("parent.doSearch('');");
                            }
                            out.println("parent.SeyLightbox.close();");
                        }
            %>
        </script>
    </body>
</html>