import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.util.Scanner;



public class CreateFile {
    Scanner cs = new Scanner(System.in);
    public void Create(String type) {
        String fileName = " ";
        System.out.print("Введите желаемое название склада или пункта продаж, который хотите открыть: ");
        System.out.println("Примеры: Склад1, ПунктПродаж1");
        String name = cs.nextLine();
        if (type.equals("Склады")) {
            fileName = "C:\\Users\\0\\Desktop\\Для проекта2\\" + type + "\\" + name + ".xls";
        } else {
            fileName = "C:\\Users\\0\\Desktop\\Для проекта2\\" + type + "\\" + name + ".xls";
        }
        try {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("Лист1");
        Sheet sheet2 = workbook.createSheet("Лист2");

        Row row1 = sheet1.createRow(0);
        Row row2 = sheet2.createRow(0);

        row1.createCell(0).setCellValue(0);
        row1.createCell(1).setCellValue("Название товара");
        row1.createCell(2).setCellValue("Цена");
        row1.createCell(3).setCellValue("Кол-во");
        row2.createCell(0).setCellValue("Улица");
        row2.createCell(1).setCellValue("Ответственный");

        FileOutputStream fileOut = new FileOutputStream(fileName);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void Delete(String type) {
        System.out.println("Введите название Склада/Пункта продаж, который хотите закрыть");
        String name = cs.nextLine();
        String fileName = "C:\\Users\\0\\Desktop\\Для проекта2\\" + type + "\\" + name + ".xls";
        File file = new File(fileName);
        file.delete();

        }

}
