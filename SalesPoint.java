import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SalesPoint {
    private String nameOfResponsible;
    private String street;
    private String salesPointname;
    private ArrayList<StoreCell> listofCells;

    public SalesPoint() {
        this.salesPointname = "";
        this.street = "";
        this.nameOfResponsible = "";
    }

    public SalesPoint(String nameOfResponsible, String salesPointname, String street) {
        this.salesPointname = salesPointname;
        this.nameOfResponsible = nameOfResponsible;
        this.street = street;
        this.listofCells = new ArrayList<>();
    }

    public void setSalesPointname(String salesPointname) {this.salesPointname = salesPointname;
    }

    public String getSalesPointname() {return salesPointname;}


    public void setStreet(String street) {this.street = street;}


    public String getStreet() {return street;}


    public void setNameOfResponsible(String nameOfResponsible, String fileName) {
        this.nameOfResponsible = nameOfResponsible;
        NewWriteFile writeFile = new NewWriteFile();
        writeFile.setFileName(fileName);
        writeFile.setResponsible(nameOfResponsible);
    }


    public String getNameOfResponsible() {return nameOfResponsible;   }


    public void addCell(String productName, int amount, int price) {
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

                this.listofCells.clear();

                for (Row row : sheet) {
                    String productName = row.getCell(0).getStringCellValue();
                    int price = (int) row.getCell(1).getNumericCellValue();
                    int amount = (int) row.getCell(2).getNumericCellValue();
                    StoreCell cell = new StoreCell(productName,amount,price);
                    cells.add(cell);
                }


            } catch (IOException e) {}
            setListofCells(cells);
    }

    public void sackResposible() {
        PrintListOfStoreHouses.List("Пункты продаж");
        this.nameOfResponsible = "";
        Scanner cs = new Scanner(System.in);
        System.out.println("Введите название пункта продаж, с которого хотите уволить сотрудника: ");
        String fileName = cs.nextLine();
        NewWriteFile file = new NewWriteFile();
        file.sackResponsible(fileName);
    }
}