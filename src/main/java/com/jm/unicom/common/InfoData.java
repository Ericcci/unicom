package com.jm.unicom.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 * <b>ProjectName:</b> permission
 * <br><b>PackageName:</b> com.jm.jmtravelApp
 * <br><b>Date:</b> 2017/12/26 11:29
 */
public class InfoData {

    private boolean ret;

    private String msg;

    private Object data;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public InfoData(boolean ret) {
        this.ret = ret;
    }

    public static InfoData success(Object object, String msg) {
        InfoData infoData = new InfoData(true);
        infoData.data = object;
        infoData.msg = msg;
        infoData.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return infoData;
    }

    public static InfoData success(Object object) {
        InfoData infoData = new InfoData(true);
        infoData.data = object;
        infoData.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return infoData;
    }

    public static InfoData success(String msg) {
        InfoData infoData = new InfoData(true);
        infoData.msg = msg;
        infoData.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return infoData;
    }

    public static InfoData success() {
        return new InfoData(true);
    }

    public static InfoData fail(String msg) {
        InfoData infoData = new InfoData(false);
        infoData.msg = msg;
        infoData.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return infoData;
    }

    public static InfoData fail(Object data,String msg) {
        InfoData infoData = new InfoData(false);
        infoData.data = data;
        infoData.msg = msg;
        infoData.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return infoData;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
