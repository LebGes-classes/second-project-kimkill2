import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewReadFile {
    private String name;

    public NewReadFile(String name) {
        this.name = name;
    }

    public void Read() {
        String filePath = "";

        if (name.contains("Список")) {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\" + name + ".xls";
        }
        if (name.contains("Склад")) {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады\\" + name + ".xls";
        }
        if (name.contains("Пункт")){
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Пункты продаж\\" + name + ".xls";
        }

        try (FileInputStream file = new FileInputStream(filePath)) {

            List<List<String>> tableData = new ArrayList<>();
            Workbook workbook = new HSSFWorkbook(file);
            int[] columnWidths = getMaxColumnWidth(workbook);
            getExcelCellInfo(workbook, tableData);

            if (name.equals("Список товаров доступных к закупке")) {
                System.out.println("Список товаров:");
                printExcelTable(tableData, columnWidths);

            } else {
                Sheet sheet1 = workbook.getSheetAt(1);
                String adress = sheet1.getRow(1).getCell(0).getStringCellValue();
                String responsible = sheet1.getRow(1).getCell(1).getStringCellValue();
                System.out.println("Улица: " + adress);
                System.out.println("Отвественный: " + responsible);
                printExcelTable(tableData, columnWidths);
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void getExcelCellInfo(Workbook workbook, List<List<String>> tableData) {

        DataFormatter formatter = new DataFormatter();
        int[] columnWidths = new int[4];
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Cell cell = row.getCell(i);
                String value = formatter.formatCellValue(cell);
                rowData.add(value);
            }

            tableData.add(rowData);
        }

    }

    public int[] getMaxColumnWidth(Workbook workbook) {
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheetAt(0);
        int[] columnWidths = new int[5];
        int mx = 0;

        for (Row row : sheet) {
            for (int i = 0; i < 4; i++) {
                Cell cell = row.getCell(i);
                String value = formatter.formatCellValue(cell);
                mx = Math.max(mx, value.length());
                columnWidths[i] = mx;
            }
        }
        return columnWidths;
    }

    public void printExcelTable(List<List<String>> tableData, int[] columnWidths) {

        columnWidths[1] = (columnWidths[1] + 1);
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (List<String> row : tableData) {

            for (int i = 0; i < row.size(); i++) {

                String value = row.get(i);
                System.out.printf("%-" + columnWidths[i] + "s |", value);

            }

            System.out.println(" ");

        }

        System.out.println("------------------------------------------------------------------------------------");
    }

    public ArrayList<String> getNamesColumn(String filePath) {

        ArrayList<String> column = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {

            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                column.add(row.getCell(1).getStringCellValue());
            }

        } catch (IOException e) {}

        return column;
    }


}
