
import java.util.Scanner;
public class Menu {
    public void menu() {
        Scanner cs = new Scanner(System.in);

        System.out.println("Добро пожаловать в магазин! ");
        System.out.println("1. Операции с товаром");
        System.out.println("2. Открыть/Закрыть Склад");
        System.out.println("3. Открыть/Закрыть пункт продаж");
        System.out.println("4. Нанять/Уволить сотрудника");
        System.out.println("5. Смена ответственного лица");
        System.out.println("6. Информация");
        System.out.println("7. Выход");
        System.out.print("Введите номер: ");
        int number = cs.nextInt();

        switch (number) {

            case 1:
                OperationsWithProduct Operations = new OperationsWithProduct();
                Operations.operations();
                break;

            case 2:
                System.out.println("Вы хотите открыть склад или закрыть?(1 - открыть, 2-закрыть)");
                int storeChoice = cs.nextInt();

                if (storeChoice == 1) {
                    CreateFile file = new CreateFile();
                    file.Create("Склады");
                    System.out.println("Склад открыт");
                } else {
                    CreateFile file = new CreateFile();
                    file.Delete("Склады");
                    System.out.println("Склад закрыт");


                }
                break;

            case 3:
                System.out.println("Вы хотите открыть пункт продаж или закрыть?(1 - открыть, 2-закрыть)");
                int salesChoice = cs.nextInt();

                if (salesChoice == 1) {
                    CreateFile file = new CreateFile();
                    file.Create("Пункты продаж");
                    System.out.println("Пункт открыт");
                } else {
                    CreateFile file = new CreateFile();
                    file.Delete("Пункты продаж");
                    System.out.println("Пункт закрыт");
                }
                break;

            case 4:

                System.out.println("Вы хотите нанять или уволить сотрудника? (1 - нанять, 2 - уволить) ");
                int choiceWhat = cs.nextInt();

                if (choiceWhat == 1) {;
                    System.out.println("На какую точку вы хотите нанять сотрудника? (1 - Склад, 2 - Пункт Продаж)");
                    String choiceWhere = cs.next();

                    if (choiceWhere.equals("1")) {

                        PrintListOfStoreHouses.List("Склады");
                        System.out.println("Введите название склада: ");
                        String place = cs.next();
                        System.out.println("Введите имя и фамилию нового сотрудника: (через тире)");
                        String nameOfResponsible = cs.next();
                        StoreHouse house = new StoreHouse();
                        nameOfResponsible = nameOfResponsible.split("-")[0] + " " + nameOfResponsible.split("-")[1];
                        house.setNameOfResponsible(nameOfResponsible, place);
                        System.out.println("Ответственный успешный сменён");

                    } else {

                        PrintListOfStoreHouses.List("Пункты продаж");
                        System.out.println("Введите название пункта продаж: ");
                        String place = cs.next();
                        System.out.println("Введите имя и фамилию нового сотрудника: ");
                        String nameOfResponsible = cs.nextLine();
                        SalesPoint house = new SalesPoint();
                        house.setNameOfResponsible(place, nameOfResponsible);
                        System.out.println("Ответственный успешный сменён");

                    }


                } else {
                    System.out.println("С какого места хотите уволить сотрудника? (1 - Склад, 2 - Пункт Продаж)");
                    String choiceWhere = cs.next();

                    if (choiceWhere.equals("1")) {
                        StoreHouse house = new StoreHouse();
                        house.sackResposible();
                        System.out.println("Сотрудник успешно уволен");

                    } else {

                        SalesPoint house = new SalesPoint();
                        house.sackResposible();
                        System.out.println("Сотрудник успешно уволен");

                    }
                }
                break;

            case 5:
                System.out.println("На какой точке вы хотите сменить ответственного сотрудника? (1 - Склад, 2 - Пункт Продаж)");
                String choiceWhere = cs.next();

                if (choiceWhere.equals("1")) {
                    PrintListOfStoreHouses.List("Склады");
                    System.out.println("Введите название склада: ");
                    String place = cs.next();
                    System.out.println("Введите имя и фамилию нового сотрудника: (через тире)");
                    String nameOfResponsible = cs.next();
                    nameOfResponsible = nameOfResponsible.split("-")[0] + " " + nameOfResponsible.split("-")[1];
                    StoreHouse house = new StoreHouse();
                    house.setNameOfResponsible(nameOfResponsible, place);
                    System.out.println("Ответственный успешный сменён");
                } else {
                    PrintListOfStoreHouses.List("Пункты продаж");
                    System.out.println("Введите название пункта продаж: ");
                    String place = cs.next();
                    System.out.println("Введите имя и фамилию нового сотрудника: ");
                    String nameOfResponsible = cs.nextLine();
                    SalesPoint house = new SalesPoint();
                    house.setNameOfResponsible(place, nameOfResponsible);
                    System.out.println("Ответственный успешный сменён");

                }
                break;

            case 6:
                InformationAbout info= new InformationAbout();
                info.information();
                break;

            case 7:
                System.exit(0);
                break;

            default:
                System.out.println("Неверная команда");
        }

    }
}
