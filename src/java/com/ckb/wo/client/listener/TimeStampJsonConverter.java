/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.client.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 *
 * @author Amaran Sidhiq
 */
public class TimeStampJsonConverter implements JsonValueProcessor {

    public TimeStampJsonConverter() {
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value, jsonConfig);
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value, jsonConfig);
    }

    private Object process(Object value, JsonConfig jsonConfig) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format((Date) value);
    } 
}
