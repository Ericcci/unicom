package com.jm.unicom.core.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;

/**
 * <b>Description:</b><br>
 * 计算目前到今天结束还有多少秒
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/5 16:37
 */
public class TimeUtil {
    public static Long getTime() {
        return 86400 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE);
    }
}
