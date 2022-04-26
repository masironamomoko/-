package com.compe.competition_demo1.util;

import com.compe.competition_demo1.cdata.Award;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjt
 * @version 1.0
 */
public class ReadAwardExcelUtil {
    //总行数
    private static int totalRows = 0;
    //总条数
    private static int totalCells = 0;
    //错误信息接收器
    private static String errorMsg;

    /**
     * 读EXCEL文件，获取信息集合
     * @return
     */
    public static List<Award> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            List<Award> userList = createExcel(mFile.getInputStream(), isExcel2003);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public static List<Award> createExcel(InputStream is, boolean isExcel2003) {
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            List<Award> awards = readExcelValue(wb);// 读取Excel里面客户的信息
            return awards;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private static List<Award> readExcelValue(Workbook wb) {
        //默认会跳过第一行标题
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Award> userList = new ArrayList<Award>();
        // 循环Excel行数
        for (int r = 0; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            Award award = new Award();
            // 循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {           //第一列
                        //如果是纯数字,将单元格类型转为String
                        if(cell.getCellTypeEnum()  == CellType.NUMERIC){
                            cell.setCellType(CellType.STRING);
                        }
                        award.setUser_num(cell.getStringCellValue());//将单元格数据赋值给user
                    }
                    else if (c == 1){
                        if(cell.getCellTypeEnum()  == CellType.NUMERIC){
                            cell.setCellType(CellType.STRING);
                        }
                        award.setCate_name(cell.getStringCellValue());
                    }
                    else if (c == 2){
                        if(cell.getCellTypeEnum()  == CellType.NUMERIC){
                            cell.setCellType(CellType.STRING);
                        }
                        award.setCom_num(cell.getStringCellValue());
                    }
                    else if (c == 3){
                        if(cell.getCellTypeEnum()  == CellType.NUMERIC){
                            cell.setCellType(CellType.STRING);
                        }
                        award.setAward_level(cell.getStringCellValue());
                    }
                }
            }
            //将excel解析出来的数据赋值给对象添加到list中
            // 添加到list
            userList.add(award);
        }
        return userList;
    }
    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }
    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
