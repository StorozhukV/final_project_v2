package settings;

import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
    private String path;

    public MyFileWriter(String path) {
        this.path = path;
    }

    public boolean write(String text, boolean append) throws IOException {
        FileWriter writer = new FileWriter(path, append);
        writer.write(text);
        writer.flush();
        writer.close();
        return true;
    }

    // Перевантажений метод "write" для запису в файл параметром "String text" (очищує файл та записує нові дані)
    public boolean write(String text) throws IOException {
        return write(text, false);
    }

    public static void writeToFile(String path, String json) throws IOException {
        MyFileWriter writer = new MyFileWriter(path);
        writer.write(json);
    }
}
