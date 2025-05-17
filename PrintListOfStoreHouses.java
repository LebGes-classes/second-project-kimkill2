import java.io.File;
public class PrintListOfStoreHouses {
    public static void List(String placeType) {
        File folder = new File("");
        if (placeType.contains("Склад")) {
            folder = new File("C:\\Users\\0\\Desktop\\Для проекта2\\Склады");
        }
        if (placeType.contains("Пункт")) {
            folder = new File("C:\\Users\\0\\Desktop\\Для проекта2\\Пункты продаж");
        }

        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {

            for (int i = 1; i < listOfFiles.length+1; i++) {

                if (listOfFiles[i-1].isFile()) {

                    System.out.println(i + ". " + listOfFiles[i-1].getName().split("\\.")[0]);

                } else if (listOfFiles[i-1].isDirectory()) {

                    System.out.println("Directory " + listOfFiles[i-1].getName());
                }
            }
        }
    }
}