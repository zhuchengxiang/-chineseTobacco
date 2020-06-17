package com.xr.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class POITest {

    public static void main(String[] args) {
        creatExcel();
    }
    /**
     * 生成excel
     */
    public static void creatExcel() {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

        //创建一个sheet
        XSSFSheet sheet1 = xssfWorkbook.createSheet("第一个新建的sheet");

        //设置高度和宽度，也可以每行每列单独分开设置
        //参数为字符个数
        sheet1.setDefaultColumnWidth(20);
        sheet1.setDefaultRowHeight((short) (33 * 20));
        //第二个参数为字符宽度的1/256
        sheet1.setColumnWidth(5, 30 * 256);

        //设置单元格样式
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();

        // 字体样式
        Font fontStyle = xssfWorkbook.createFont();
        fontStyle.setBold(true);
        // 字体
        fontStyle.setFontName("等线");
        // 大小
        fontStyle.setFontHeightInPoints((short) 11);
        // 将字体样式添加到单元格样式中
        cellStyle.setFont(fontStyle);

        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置 单元格填充色
        DefaultIndexedColorMap defaultIndexedColorMap = new DefaultIndexedColorMap();
        XSSFColor clr = new XSSFColor(defaultIndexedColorMap);
        byte[] bytes = {
                (byte) 217,
                (byte) 217,
                (byte) 217
        };
        clr.setRGB(bytes);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(clr);

        //设置单元格不为锁定，可编辑，反是用了这个样式的都可编辑
        cellStyle.setLocked(false);
        //锁定整个sheet不可编辑
        sheet1.protectSheet("1231312");


        //创建一行数据
        XSSFRow row;
        XSSFCell cell;
        row = sheet1.createRow(0);
        cell = row.createCell(0);
        //设值
        cell.setCellValue("2");

        //合并单元格
        CellRangeAddress cra = new CellRangeAddress(1, 1, 0, 3); // 起始行, 终止行, 起始列, 终止列
        sheet1.addMergedRegion(cra);
        //设置合并单元格的样式
        // 使用RegionUtil类为合并后的单元格添加边框
        // 下边框
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM_DASHED, cra, sheet1);
        // 左边框
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM_DASHED, cra, sheet1);
        row = sheet1.getRow(1);

        //设置合并单元格内的文本样式
        //但这个单元格的边框样式会覆盖上面设置的合并单元格的样式
        CellUtil.getCell(row, 0).setCellStyle(cellStyle);


        //设置单个单元格的样式
        row = sheet1.createRow(2);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);

        //设置数据校验
        //序列校验
        String[] strArray = {
                "星期一",
                "星期二",
                "星期三"
        };
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet1);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(strArray);
        CellRangeAddressList addressList = new CellRangeAddressList(3, 3, 0, 2);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        //显示报错提示框
        validation.setShowErrorBox(true);
        validation.createErrorBox("错误提示", "只能选择指定的内容！");
        //设置单元格右侧显示剪头符号,显示可用的选项，默认为true
        validation.setSuppressDropDownArrow(true);
        //显示提示信息
        validation.setShowPromptBox(true);
        validation.createPromptBox("提示信息", "请选择星期填入！");
        sheet1.addValidationData(validation);

        //保护工作薄不可被修改
        xssfWorkbook.lockStructure();
        //这个不知道有啥用
        xssfWorkbook.lockRevision();
        //锁定excel的窗口大小，不能无限制的横向，纵向拉伸。
        xssfWorkbook.lockWindows();

        xssfWorkbook.createSheet("第二个人sheet");


        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("/aaaa/创建excel.xlsx");
            xssfWorkbook.write(outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
