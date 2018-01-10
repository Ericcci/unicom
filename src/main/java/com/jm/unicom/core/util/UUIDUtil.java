package com.jm.unicom.core.util;

import java.util.UUID;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/10 16:29
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
