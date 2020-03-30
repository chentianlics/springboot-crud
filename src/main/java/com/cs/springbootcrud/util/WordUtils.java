package com.cs.springbootcrud.util;

import cn.afterturn.easypoi.word.WordExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @program
 * @description: word工具类
 * @author:
 * @create: 2019/01/10 11:33
 */
@Slf4j
public class WordUtils {

    /**
     * 根据参数与文件路径生成word文件
     * @param
     * @param wordPath
     */
    public static void saveWord(String wordPath,Map<String, Object> param,String name) {
        try {
            ZipSecureFile.setMinInflateRatio(-1.0d);
            String templatePath= "templates/name.docx";
            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, param);
            FileOutputStream out = new FileOutputStream(wordPath +name+ DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmssSSS") + ".docx");
            doc.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("word模板写入失败");
            e.printStackTrace();
        }
    }

    public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params, HttpServletResponse response) {
        try {

            ZipSecureFile.setMinInflateRatio(-1.0d);
            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);
            String tmpPath = temDir + fileName;
            FileOutputStream fos = new FileOutputStream(tmpPath);
            doc.write(fos);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream out = response.getOutputStream();
            doc.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteDir(temDir);
        }

    }
}
