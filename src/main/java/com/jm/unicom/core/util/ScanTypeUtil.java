package com.jm.unicom.core.util;

import com.jm.unicom.core.common.ConstantClassField;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/8 18:29
 */
public class ScanTypeUtil {
    public static Integer isWechatOrAlipay(HttpServletRequest request) {
        String type = request.getHeader("user-agent");
        if (type.contains(ConstantClassField.ALIPAY)) {
            return 2;
        } else if (type.contains(ConstantClassField.WECHAT)) {
            return 1;
        } else {
            return -1;
        }
    }
}
