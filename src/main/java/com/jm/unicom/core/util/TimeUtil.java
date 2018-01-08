package com.jm.unicom.core.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;

/**
 * <b>Description:</b><br>
 * 时间工具类
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/5 16:37
 */
public class TimeUtil {
    /**
     * 计算现在到今天过完还剩下多少秒
     * @return Long
     */
    public static Long todayOverTime() {
        return 86400 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE);
    }
}
