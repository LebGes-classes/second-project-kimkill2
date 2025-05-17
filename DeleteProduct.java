
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DeleteProduct {
    private String name;
    private int amount;
    private String fileName;

    public DeleteProduct(String name, int amount, String fileName) {
        this.name = name;
        this.amount = amount;
        this.fileName = fileName;
    }

    public int delete() {
        int neededRow = 0;
        String filePath = "";

        if (fileName.equals("Список товаров доступных к закупке")) {

            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\" + fileName + ".xls";

        }
        if (fileName.contains("Склад")) {

            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады\\" + fileName + ".xls";

        }
        if (fileName.contains("ПунктПродаж")) {

            filePath = "C:\\Users\\0\\Desktop\\ДЛя проекта2\\Пункты продаж\\" + fileName + ".xls";

        }

        try (FileInputStream file = new FileInputStream(filePath)) {

            Workbook workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                String cellName = sheet.getRow(i).getCell(1).getStringCellValue();

                if (cellName.equals(name)) {
                    neededRow = i;

                }
            }

            if (neededRow != 0) {
                double cellValue = sheet.getRow(neededRow).getCell(3).getNumericCellValue();
                double newCellValue = cellValue - amount;


                if (newCellValue == 0) {

                } else {

                    sheet.getRow(neededRow).getCell(3).setCellValue(newCellValue);

                }

                FileOutputStream fos = new FileOutputStream(filePath);
                workbook.write(fos);
                fos.close();
                return (int) sheet.getRow(neededRow).getCell(2).getNumericCellValue();

            } else {

                double cellValue = sheet.getRow(neededRow).getCell(3).getNumericCellValue();
                double newCellValue = cellValue - amount;
                if (newCellValue == 0) {

                    sheet.getRow(neededRow).getCell(0).setCellValue("");
                    sheet.getRow(neededRow).getCell(1).setCellValue("");
                    sheet.getRow(neededRow).getCell(2).setCellValue("");
                    sheet.getRow(neededRow).getCell(3).setCellValue("");

                } else {

                    sheet.getRow(neededRow).getCell(3).setCellValue(newCellValue);

                }

                FileOutputStream fos = new FileOutputStream(filePath);
                workbook.write(fos);
                fos.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
