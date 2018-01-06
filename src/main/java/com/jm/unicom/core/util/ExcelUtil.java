package com.jm.unicom.core.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jm.unicom.api.shop.entity.Shop;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Excel导入导出工具(解决导出中文乱码的问题)
 *
 * @author Eric.
 * @version 1.0
 * @date 2018-01-06 21:28:39
 */
public class ExcelUtil {
    public static void exportExcel(List<Shop> shopList, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("店铺详情.xls".getBytes("gb2312"), "ISO8859-1"));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Shop.class, shopList);
        workbook.write(response.getOutputStream());
    }
}
