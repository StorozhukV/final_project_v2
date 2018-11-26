package settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFileReader {
    // Шлях до файлу
    private String path;

    // Конструктор
    public MyFileReader(String path) {
        this.path = path;
    }

    public String read() throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(path);
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\Z");
        String data = scanner.next();
        scanner.close();
        return data;
    }

    // Метод для зчитування з файлу
    public static String readFromFile(String path) throws FileNotFoundException {
        MyFileReader reader = new MyFileReader(path);
        String text = reader.read();
        return text;
    }
}
