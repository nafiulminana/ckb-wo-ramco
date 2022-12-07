/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.displaytag;

import com.ckb.wo.client.model.Location;
import com.ckb.wo.client.model.ServiceModeDetail;
import com.ckb.wo.client.model.Vendor;
import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author Admin
 */
public class JavascriptColumnWrapper extends TableDecorator{

    public String getVendorscriptlink(){
        Vendor vendor = (Vendor) getCurrentRowObject();
        return  "<a href=\"javascript:choose("+vendor.getTvendorPk()+",'"+vendor.getVendorname()+"')\" >"+vendor.getVendorname()+"</a>";
    }
    
    public String getServicemodedetailscriptlink(){
        ServiceModeDetail smdetail = (ServiceModeDetail) getCurrentRowObject();
        return  "<a href=\"javascript:choose("+smdetail.getTservicemodedetailPk()+",'"+smdetail.getSmdcode()+" "+smdetail.getSmdname()+"')\" >"+smdetail.getSmdcode()+"</a>";
    }

    public String getLocationscriptlink(){
        Location location = (Location) getCurrentRowObject();
        return  "<a href=\"javascript:choose("+location.getTlocationPk()+",'"+location.getLocationcode()+" "+location.getLocationname()+"')\" >choose</a>";
    }
}
