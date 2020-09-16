package com.example.demo;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ReadExcelFile {

    XSSFWorkbook wb;
    XSSFSheet sheet;

    public ReadExcelFile(String excelPath){
        try{
            File src = new File(excelPath);
            FileInputStream fileStream = new FileInputStream(src);
            wb = new XSSFWorkbook(fileStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetNumber, int row, int column){
        sheet = wb.getSheetAt(sheetNumber);
        String data;
        data = sheet.getRow(row).getCell(column).getStringCellValue();

        return data;
    }

    public int getRowCount(int sheetIndex){
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
    }
}
