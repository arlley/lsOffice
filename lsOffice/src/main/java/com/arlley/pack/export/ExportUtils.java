package com.arlley.pack.export;

import com.arlley.pack.entity.File;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class ExportUtils {


    public static Map<String, String> getParamMap(Object object, Class clazz) throws IllegalAccessException {
        Map<String, String> result = new HashMap<>();

        for(Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            result.put("${"+field.getName()+"}11", (String) field.get(object));
        }
        return result;
    }

    public static XWPFDocument export(Map<String, String> paramMap, String templatePath) throws IOException {
        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(templatePath));

        Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
            Set<String> set = paramMap.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<XWPFRun> run=paragraph.getRuns();
                for(int i=0;i<run.size();i++)
                {
                    if(run.get(i).getText(run.get(i).getTextPosition())!=null &&
                            run.get(i).getText(run.get(i).getTextPosition()).equals(key))
                    {
                        /**
                         * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                         * 就可以把原来的文字全部替换掉了
                         */
                        run.get(i).setText(paramMap.get(key),0);
                    }
                }
            }

        }

        Iterator<XWPFTable> itTable = document.getTablesIterator();
        while (itTable.hasNext()) {
            XWPFTable table = (XWPFTable) itTable.next();
            int count = table.getNumberOfRows();
            for (int i = 0; i < count; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    for (Map.Entry<String, String> e : paramMap.entrySet()) {
                        if (cell.getText().equals(e.getKey())) {
                            cell.removeParagraph(0);
                            cell.setText(e.getValue());
                        }
                    }
                }
            }
        }
        return document;
    }
}
