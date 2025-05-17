import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StoreHouse {
    private String street;
    private String nameOfResponsible;
    private String storeHouseName;
    private ArrayList<StoreCell> listofCells;

    public StoreHouse() {
        this.street = "";
        this.storeHouseName = "";
        this.nameOfResponsible = "";
    }

    public StoreHouse(String street, String name, String nameOfresponsible) {
        this.street = street;
        this.storeHouseName = name;
        this.nameOfResponsible = nameOfresponsible;
        this.listofCells = new ArrayList<>();
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStoreHouseName(String storeHouseName) {
        this.storeHouseName = storeHouseName;
    }

    public String getStoreHouseName() {
        return storeHouseName;
    }

    public void setNameOfResponsible(String nameOfResponsible, String fileName) {
        this.nameOfResponsible = nameOfResponsible;
        NewWriteFile writeFile = new NewWriteFile();
        writeFile.setFileName(fileName);
        writeFile.setResponsible(nameOfResponsible);
    }

    public String getNameOfResponsible() {
        return nameOfResponsible;
    }

    public ArrayList<StoreCell> getListofCells() {
        return this.listofCells;
    }

    public void addCell(String productName, int amount, int price) {
        if (listofCells == null) {
            listofCells = new ArrayList<>(); // Инициализация, если список null
        }
        for (StoreCell Cell : listofCells) {
            if (Cell.getProductName().equals(productName)) {
                System.out.println("Данная ячейка уже есть");
                Cell.addAmount(amount);
                System.out.println("Количество товара в ячейке обновлено");
            }
        }
        StoreCell cell = new StoreCell(productName, amount, price);
        this.listofCells.add(cell);


    }

    public void createCell(String productName, int amount, int price) {
        StoreCell cell = new StoreCell(productName, amount, price);
        this.listofCells.add(cell);
    }


    public void setListofCells(ArrayList<StoreCell> cells) {
        this.listofCells = cells;
    }
    public void setListofCells(String fileName) {
        String filePath = "";
        ArrayList<StoreCell> cells = new ArrayList<>();
        if (fileName.contains("Склад")) {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады" + fileName + ".xls";
        } else {
            filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Пункты продаж" + fileName + ".xls";
        }
        try (FileInputStream fis = new FileInputStream(filePath)) {

            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String productName = row.getCell(0).getStringCellValue();
                int price = (int) row.getCell(1).getNumericCellValue();
                int amount = (int) row.getCell(2).getNumericCellValue();
                StoreCell cell = new StoreCell(productName, amount, price);
                cells.add(cell);

            }
        } catch (IOException e) {}
        setListofCells(cells);
    }


    public void sackResposible() {
        this.nameOfResponsible = "";
        Scanner cs = new Scanner(System.in);
        PrintListOfStoreHouses.List("Склады");
        System.out.println("Введите название склада, с которого хотите уволить сотрудника: ");
        String fileName = cs.nextLine();
        NewWriteFile file = new NewWriteFile();
        file.sackResponsible(fileName);
    }
}
