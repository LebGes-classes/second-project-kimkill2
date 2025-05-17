import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
 class OperationsWithProduct {

     NewReadFile file = new NewReadFile("Список товаров доступных к закупке");
     Scanner cs = new Scanner(System.in);
     Product product = new Product();

     public void operations() {
         System.out.println("1. Переместить товар");
         System.out.println("2. Продать товар");
         System.out.println("3. Закупить товар");
         System.out.println("4. Возврат в меню");
         System.out.println("Введите номер желаемой операции: ");
         String number = cs.next();

         switch (number) {

             case "1":
                 PrintListOfStoreHouses.List("Склады");
                 System.out.println("Введите название Склада из которого нужно забрать товар: ");
                 String fileName = cs.next();
                 NameCheck(fileName);
                 System.out.println("Проверка на имя пройдена");
                 product.TransportProduct(fileName);
                 break;

             case "2":
                 PrintListOfStoreHouses.List("Пункт продаж");
                 System.out.println("Введите название Пункта Продаж, из которого хотите продать товар: ");
                 String name = cs.next();
                 NameCheck(name);
                 product.deleteProduct(name);
                 break;

             case "3":
                 file.Read();
                 NameCheck("Список товаров доступных к закупке");
                 product.buyProduct();
                 break;

             case "4":
                 Menu menu = new Menu();
                 menu.menu();

             default:
                 System.out.println("Введена неверная команда");
         }

     }


     public String NameCheck(String fileName) {
         NewReadFile readFile = new NewReadFile(fileName);
         if (fileName.contains("Склад")) {
             readFile.Read();
             System.out.println("Введите имя желаемого продукта: ");
             String productName = cs.next();
             System.out.print("Введите кол-во продукта:");
             String amount = cs.next();
             product.setName(productName);
             product.setAmount(Integer.valueOf(amount));
             return fileName;
         }
         if (fileName.equals("Список товаров доступных к закупке")) {
             readFile.Read();
             System.out.println("Введите имя желаемого продукта: ");
             String productName = cs.next();
             System.out.print("Введите кол-во продукта:");
             String amount = cs.next();
             product.setName(productName);
             product.setAmount(Integer.valueOf(amount));
             return fileName;
         }
         if (fileName.contains("Пункт")) {
             readFile.Read();
             System.out.println("Введите имя желаемого продукта: ");
             String productName = cs.next();
             System.out.print("Введите кол-во продукта:");
             String amount = cs.next();
             product.setName(productName);
             product.setAmount(Integer.valueOf(amount));
             return fileName;

         }
         return "";
     }

//     public void nameCheck(String fileName) {
//         boolean flag = false;
//         String filePath = "";
//         System.out.println("Введите имя продукта: ");
//         String name = cs.next();
//
//         if (fileName.equals("Список товаров доступных к закупке")) {
//             filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\" + fileName + ".xls";
//
//         } else {
//             filePath = "C:\\Users\\0\\Desktop\\Для проекта2\\Склады\\" + fileName + ".xls";
//         }
//
//         ArrayList<String> listOFNames = file.getNamesColumn(filePath);
//
//         if (listOFNames.contains(name)) {
//
//             System.out.println("Введите кол-во продукта: ");
//             int amount = cs.nextInt();
//             product.setName(name);
//             product.setAmount(amount);
//
//         } else {
//
//             System.out.println("Продукт не найден");
//             nameCheck(fileName);
//
//         }
//     }
 }
