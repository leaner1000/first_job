package com.javan.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PdfUtils {

    public static void addToMap(Map<String,String> m,Float f){  //将金额按位数存入map
        if(f>0.1){
            m.put("jiao",numToString((int)(f%1/0.1)));
        }
        if(f>1){
            m.put("yuan",numToString((int)(f%10/1)));
        }
        if(f>10){
            m.put("shi",numToString((int)(f%100/10)));
        }
        if(f>100){
            m.put("bai",numToString((int)(f%1000/100)));
        }
        if(f>1000){
            m.put("qian",numToString((int)(f%10000/1000)));
        }
        if(f>10000){
            m.put("wan",numToString((int)(f%100000/10000)));
        }
        if(f>100000){
            m.put("shiwan",numToString((int)(f/100000)));
        }
    }

    public static String numToString(int num){
        switch (num){
            case 1:
                return "壹";
            case 2:
                return "贰";
            case 3:
                return "叁";
            case 4:
                return "肆";
            case 5:
                return "伍";
            case 6:
                return "陆";
            case 7:
                return "柒";
            case 8:
                return "捌";
            case 9:
                return "玖";
            case 0:
                return "零";
                default:
                    return "";
        }
    }

    public static ByteArrayOutputStream generatePdfStream(String fileName, String fontName, Map<String, String> data) throws  Exception{
        PdfReader reader = new PdfReader(fileName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* 将要生成的目标PDF文件名称 */
        PdfStamper ps = new PdfStamper(reader, bos);

        PdfContentByte under = ps.getUnderContent(1);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);
        /* 取出报表模板中的所有字段 */
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        fillData(fields, data);

        /* 必须要调用这个，否则文档不会生成的 */
        ps.setFormFlattening(true);
        ps.close();
        return bos;
    }

    public static void fillData(AcroFields fields, Map<String, String> data)
            throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写
        }
    }
}
