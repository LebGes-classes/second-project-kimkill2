import java.util.Scanner;
public class InformationAbout {
    Scanner cs = new Scanner(System.in);
    public void information() {
        System.out.println("1. Информация о товарах на складе/пункте продаж");
        System.out.println("2. Информация о товарах доступных к закупке");
        System.out.println("Введите номер желаемой операции: ");

        int number = cs.nextInt();
        switch (number) {
            case 1:
                System.out.println("Введите 1 - склад, 2 - пункт продаж:");
                String choice = cs.next();
                if (choice.equals("1")) {
                    PrintListOfStoreHouses list = new PrintListOfStoreHouses();
                    list.List("Склады");
                    System.out.println("Введите название склада: ");
                    String fileName = cs.next();
                    NewReadFile file = new NewReadFile(fileName);
                    file.Read();


                }
                else {
                    PrintListOfStoreHouses list = new PrintListOfStoreHouses();
                    list.List("Пункты продаж");
                    System.out.println("Введите название пункта продаж: ");
                    String fileName = cs.next();
                    NewReadFile file = new NewReadFile(fileName);
                    file.Read();
                }
                break;
            case 2:
                NewReadFile file = new NewReadFile("Список товаров доступных к закупке");
                file.Read();
                break;
        }
    }
}
