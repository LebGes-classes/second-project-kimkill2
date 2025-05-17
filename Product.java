import java.util.Scanner;

public class Product {

    private String Name;
    private int price;
    private int amount;
    Scanner cs = new Scanner(System.in);
    PrintListOfStoreHouses list = new PrintListOfStoreHouses();

    public Product() {
        this.Name = "";
        this.price = 0;
        this.amount = 0;
    }

    public Product(String name, int amount) {
        this.Name = name;
        this.amount = amount;
    }

    public void setName(String currentName) {

        if (currentName.length() != 0) {
            this.Name = currentName;
        } else {
            System.out.println("Данное название не подходит");
        }

    }

    public String getName() {
        return Name;
    }

    public void setPrice(int price) {

        if (price > 0) {
            this.price = price;
        } else{
            System.out.println("Цена товара не может быть меньше равна нулю");
        }

    }

    public int getPrice() {return price;}

    public void setAmount(int amount) {

        if (amount > 0) {
            this.amount = amount;
        } else {
            System.out.println("Количество не может равняться нулю");
        }

    }

    public int getAmount() {return  amount;}

    public void addPorduct(int price) {
        PrintListOfStoreHouses.List("Склады");
        System.out.println("Введите название Склада или Пункта продаж в который нужно переместить: ");
        String fileName = cs.nextLine();
        NewWriteFile changedFile = new NewWriteFile(fileName, Name, amount, price);
        changedFile.Write();

        if (fileName.contains("Склад")) {
            StoreHouse place = new StoreHouse();
            place.setListofCells(fileName);
            place.addCell(Name, amount, price);
        } else{
            SalesPoint place = new SalesPoint();
            place.setListofCells(fileName);
            place.addCell(Name, amount, price);
        }

        System.out.println("Товар добавлен");
    }

    public void buyProduct() {

        int price = deleteProduct("Список товаров доступных к закупке");
        addPorduct(price);

    }

    public int deleteProduct(String fileName) {

        DeleteProduct fileWithDelProduct = new DeleteProduct(Name, amount, fileName);
        int price = fileWithDelProduct.delete();
        System.out.println("Товар удалён");
        return price;
    }

    public void TransportProduct(String fileName) {

        DeleteProduct fileWithDelProduct = new DeleteProduct(Name, amount, fileName);
        int price = fileWithDelProduct.delete();
        addPorduct(price);

    }
}
