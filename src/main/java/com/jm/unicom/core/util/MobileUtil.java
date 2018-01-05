package com.jm.unicom.core.util;

import com.jm.unicom.common.ConstantClassField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 * <b>ProjectName:</b> unicom
 * <br><b>PackageName:</b> com.jm.unicom.core.util
 * <br><b>Date:</b> 2018/1/5 18:30
 */
public class MobileUtil {
    /**
     * 判断传入的参数号码为哪家运营商
     * <p>
     * 1、移动号段有1340~1348,135,136,137, 138,139,1440,147,150,151, 152,157,158,159,178,182,183,184,187,188,198。
     * 2、联通号段有130，131，132,145,146，155，156，166,175,176,185，186。
     * 3、电信号段有133,1349,1410,149，153,173,17400-17405,177,180，181，189,199。
     *
     * @param mobile 手机号码
     * @return 运营商名称
     */
    public static int validateMobile(String mobile) {

        int carrieroperator = -1;

        //中国移动
        if (mobile.trim().substring(0, 4).equals("1340") || mobile.trim().substring(0, 4).equals("1341") || mobile.trim().substring(0, 4).equals("1342") || mobile.trim().substring(0, 4).equals("1343") || mobile.trim().substring(0, 4).equals("1344") || mobile.trim().substring(0, 4).equals("1345") || mobile.trim().substring(0, 4).equals("1346") || mobile.trim().substring(0, 4).equals("1347") || mobile.trim().substring(0, 4).equals("1348")
                || mobile.trim().substring(0, 3).equals("135") || mobile.trim().substring(0, 3).equals("136") || mobile.trim().substring(0, 3).equals("137")
                || mobile.trim().substring(0, 3).equals("138") || mobile.trim().substring(0, 3).equals("139") || mobile.trim().substring(0, 4).equals("1440") || mobile.trim().substring(0, 3).equals("147") || mobile.trim().substring(0, 3).equals("150") ||
                mobile.trim().substring(0, 3).equals("151") || mobile.trim().substring(0, 3).equals("152")
                || mobile.trim().substring(0, 3).equals("157") || mobile.trim().substring(0, 3).equals("158") || mobile.trim().substring(0, 3).equals("159")
                || mobile.trim().substring(0, 3).equals("172") || mobile.trim().substring(0, 3).equals("182") || mobile.trim().substring(0, 3).equals("183") || mobile.trim().substring(0, 3).equals("184")
                || mobile.trim().substring(0, 3).equals("187") || mobile.trim().substring(0, 3).equals("188") || mobile.trim().substring(0, 3).equals("198")) {
            carrieroperator = 1;
        }
        //中国联通 130，131，132,145,146，155，156，166,175,176,185，186。
        if (mobile.trim().substring(0, 3).equals("130") || mobile.trim().substring(0, 3).equals("131") ||
                mobile.trim().substring(0, 3).equals("132") || mobile.trim().substring(0, 3).equals("145") || mobile.trim().substring(0, 3).equals("146") || mobile.trim().substring(0, 3).equals("155") || mobile.trim().substring(0, 3).equals("156")
                || mobile.trim().substring(0, 3).equals("166") || mobile.trim().substring(0, 3).equals("175") || mobile.trim().substring(0, 3).equals("176") || mobile.trim().substring(0, 3).equals("185") || mobile.trim().substring(0, 3).equals("186")) {
            carrieroperator = 2;
        }
        //中国电信 133,1349,1410,149，153,173,17400-17405,177,180，181，189,199。
        if (mobile.trim().substring(0, 3).equals("133") || mobile.trim().substring(0, 4).equals("1349") || mobile.trim().substring(0, 4).equals("1410") || mobile.trim().substring(0, 3).equals("149") || mobile.trim().substring(0, 3).equals("153") ||
                mobile.trim().substring(0, 3).equals("173") || mobile.trim().substring(0, 5).equals("17400") || mobile.trim().substring(0, 5).equals("17401") || mobile.trim().substring(0, 5).equals("17402") || mobile.trim().substring(0, 5).equals("17403") || mobile.trim().substring(0, 5).equals("17404") || mobile.trim().substring(0, 5).equals("17405") || mobile.trim().substring(0, 3).equals("180")
                || mobile.trim().substring(0, 3).equals("181") || mobile.trim().substring(0, 3).equals("189") || mobile.trim().substring(0, 3).equals("199")) {
            carrieroperator = 3;
        }

        return carrieroperator;
    }

    public static Elements getMobileFrom(String mobileNumber) throws Exception {
        String url = ConstantClassField.PHONE_FROM + mobileNumber;
        url = String.format(url, mobileNumber);
        Document doc = Jsoup.connect(url).get();
        return doc.getElementsByClass("tdc2");
    }
}

