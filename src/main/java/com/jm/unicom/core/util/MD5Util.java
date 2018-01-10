package com.jm.unicom.core.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/10 15:42
 */
public class MD5Util {
    public static String getMD5Password(String password, String salt) {
        return String.valueOf(new SimpleHash("MD5", password, salt));
    }
}
