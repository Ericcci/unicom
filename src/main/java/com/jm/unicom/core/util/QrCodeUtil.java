package com.jm.unicom.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * QrCodeUtil
 *
 * @author Eric
 * @date 2018/1/2
 */
@Slf4j
public class QrCodeUtil {
    public static String getQrCode(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        byte[] imageData = EntityUtils.toByteArray(entity);
        httpclient.close();
        return Base64Util.byte2Base64StringFun(imageData);
    }
}

