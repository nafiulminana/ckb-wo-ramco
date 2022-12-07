/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.client.model;


import java.io.Serializable;

/**
 *
 * @author Amaran Sidhiq
 */
public class MenuItem implements Serializable {
    String caption,value;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MenuItem() {
    }

    public MenuItem(String caption, String value) {
        this.caption = caption;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[Caption: "+caption+",Value: "+value+"]";
    }
}
