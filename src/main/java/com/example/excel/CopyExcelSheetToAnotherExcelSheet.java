package com.example.excel;

import com.example.util.KettleUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CopyExcelSheetToAnotherExcelSheet {

    public String copyExcel(String filename, String build_type) throws IOException {
        //定义分部分项的清单表
        String partia3 = "分部分项工程清单计价表";
        //定义单价措施项目清单表
        String unit3 = "单价措施项目清单计价表";
        //定义总价措施
        String all3 = "总价措施";
        //定义规费税金
        String fees3 = "规费";
        //材料和工程设备
        String mer3 = "材料和工程设备";
        //返回的项目名称，从数据中获取的
        String maplnfo_name = null;

        // 保存新EXCEL路径
        String toPath = "D:\\ok\\";
        //String toPath = "/bigdata/monitor/";
        // 打开已有的excel
        String fileurl = "F:\\test\\" + filename;
        // String fileurl = "/bigdata/upload/"+filename;
        InputStream in = new FileInputStream(fileurl);
        HSSFWorkbook wb = new HSSFWorkbook(in);
        for (int ii = 0; ii < wb.getNumberOfSheets(); ii++) {
            // 创建新的excel
            HSSFWorkbook wbCreat = new HSSFWorkbook();
            HSSFSheet sheet = wb.getSheetAt(ii);
            HSSFSheet sheetCreat = wbCreat.createSheet(sheet.getSheetName());

            // 复制源表中的合并单元格
            MergerRegion(sheetCreat, sheet);
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();
            for (int i = firstRow; i <= lastRow; i++) {
                // 创建新建excel Sheet的行
                HSSFRow rowCreat = sheetCreat.createRow(i);
                // 取得源有excel Sheet的行
                HSSFRow row = sheet.getRow(i);


                // 单元格式样
                int firstCell = row.getFirstCellNum();
                int lastCell = row.getLastCellNum();
                for (int j = firstCell; j < lastCell; j++) {
                    // 自动适应列宽 貌似不起作用
                    sheetCreat.autoSizeColumn(j);
                    rowCreat.createCell(j);
                    String strVal = "";
                    String files = filename + "." + sheet.getSheetName() + ".xls";
                    if (row.getCell(j) == null) {

                    } else {
                        strVal = removeInternalBlank(row.getCell(j).getStringCellValue());
                    }
                    rowCreat.getCell(j).setCellValue(strVal);
                }
            }
            String files = filename + "." + sheet.getSheetName() + ".xls";
            FileOutputStream fileOut = new FileOutputStream(toPath + files);
            wbCreat.write(fileOut);
            fileOut.close();
            //判断是否生成分部分项表如果有，则执行kettle清洗数据
            if (files != null && files.contains(partia3)) {
                KettleUtil kettleUtil = new KettleUtil();
                maplnfo_name = kettleUtil.kettleCleanOut(files, "partial3");
            } else if (files != null && files.contains(unit3)) {
                KettleUtil kettleUtil = new KettleUtil();
                kettleUtil.kettleCleanOut(files, "unit3");
            } else if (files != null && files.contains(all3)) {
                KettleUtil kettleUtil = new KettleUtil();
                kettleUtil.kettleCleanOut(files, "all3");
            } else if (files != null && files.contains(fees3)) {
                KettleUtil kettleUtil = new KettleUtil();
                kettleUtil.kettleCleanOut(files, "fees3");
            } else if (files != null && files.contains(mer3)) {
                KettleUtil kettleUtil = new KettleUtil();
                kettleUtil.kettleCleanOut(files, "mer3");
            } else if (files == null) {
                System.out.println("文件正在解析");

            }
        }
        return maplnfo_name;
    }

    /**
     *  * 复制原有sheet的合并单元格到新创建的sheet
     *  *
     *  * @param sheetCreat
     *    新创建sheet
     *  * @param sheet
     *    原有的sheet
     */
    private static void MergerRegion(HSSFSheet sheetCreat, HSSFSheet sheet) {
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            Region mergedRegionAt = sheet.getMergedRegionAt(i);
            sheetCreat.addMergedRegion(mergedRegionAt);
        }

    }

    /**
     *  * 去除字符串内部空格
     *  
     */
    public static String removeInternalBlank(String s) {
// System.out.println("bb:" + s);
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(s);
        char str[] = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                sb.append(' ');
            } else {
                break;
            }
        }
        String after = m.replaceAll("");
        return sb.toString() + after;
    }
}
