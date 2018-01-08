package com.jm.unicom.core.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.core.util
 *          <br><b>Date:</b> 2018/1/8 17:26
 */
public class ImageUtil {
    public static String uploadImage(MultipartFile file) throws IOException {
        byte[] imageData = file.getBytes();
        return Base64Util.byte2Base64StringFun(imageData);
    }
}
