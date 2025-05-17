import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewWriteFile {
    private String fileName;
    private String productName;
    private int amount;
    private int price;

    public NewWriteFile(String fileName,String productName, int amount, int price) {
        this.fileName = fileName;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }
    public NewWriteFile() {}

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int Write() {
        Scanner cs = new Scanner(System.in);
        String filePath = "";

        if (fileName.contains("Склад")) {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады\\" + fileName + ".xls";
        } else {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Пункты продаж\\" + fileName + ".xls";
        }

        try (FileInputStream file = new FileInputStream(filePath)) {

            Workbook workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum() + 1;
            ArrayList<String> namesColumn = getNamesColumn(lastRowNum, sheet);

            if (namesColumn.contains(productName)) { // проверяется, есть ли товар на складе

                int rowNum = getRowNum(sheet, productName);
                Row row = sheet.getRow(rowNum);
                row.getCell(3).setCellValue(row.getCell(3).getNumericCellValue() + amount);
                FileOutputStream fos = new FileOutputStream(filePath);
                workbook.write(fos);
                fos.close();

            } else {
                Row row = sheet.createRow(lastRowNum);
                row.createCell(0).setCellValue(lastRowNum + ".");
                row.createCell(1).setCellValue(productName);
                row.createCell(2).setCellValue(price);
                row.createCell(3).setCellValue(amount);
                FileOutputStream fos = new FileOutputStream(filePath);
                workbook.write(fos);
                fos.close();
                return price;
            }



        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
        return 1;
    }
    public ArrayList<String> getNamesColumn(int lastRowNum, Sheet sheet) {

        ArrayList<String> column = new ArrayList<>();

        for (Row row : sheet) {
            column.add(row.getCell(1).getStringCellValue());
        }

        return column;
    }

    public int getRowNum(Sheet sheet, String productName) {

        int result = 0;
        int i = 0;

        for (Row row : sheet) {
            if (row.getCell(1).getStringCellValue().equals(productName)) {
                result = i;
            }
            i++;
        }

        return result;
    }

    public void sackResponsible(String fileName) {
        String filePath = "";

        if (fileName.contains("Склад")) {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады\\" + fileName + ".xls";
        } else {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Пункты продаж\\" + fileName + ".xls";
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {

            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(1);
            Row row = sheet.getRow(1);
            row.getCell(1).setCellValue("");
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
        }catch (IOException e) {}
    }

    public void setResponsible(String nameOfResponsible) {
        String filePath = "";

        if (fileName.contains("Склад")) {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады\\" + fileName + ".xls";
        } else {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Пункты продаж\\" + fileName + ".xls";
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {

            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(1);
            Row row = sheet.getRow(1);
            row.getCell(1).setCellValue(nameOfResponsible);
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();

        }catch (IOException e) {}
    }
}