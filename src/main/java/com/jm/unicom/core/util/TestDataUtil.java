package com.jm.unicom.core.util;

import com.jm.unicom.api.entity.Shop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/9 10:05
 */
public class TestDataUtil {
    public static List<Shop> getTestData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "2013-12-12 12:12:12";
        List<Shop> shopList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Shop shop = new Shop();
            shop.setShopName("杂货铺" + i);
            shop.setShopKeeper("eric");
            shop.setStatus(1);
            shop.setShopWechat("wechat");
            shop.setBankNo("6228480402564890018");
            shop.setCreateTime(sdf.parse(time));
            shop.setShopAddress("福建省晋江市陈埭镇泉商投资大厦2306");
            shop.setShopQq("380652070");
            shop.setTelpohone("18815999677");
            shopList.add(shop);
        }
        return shopList;
    }
}
